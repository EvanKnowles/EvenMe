package za.co.knonchalant.evenme;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.objectbank.ObjectBank;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;

import java.io.IOException;
import java.util.*;

public class NLP {
    private final AbstractSequenceClassifier<CoreLabel> classifier;
    StanfordCoreNLP pipeline;

    public NLP() throws IOException, ClassNotFoundException {
        // Create the Stanford CoreNLP pipeline
        Properties props = PropertiesUtils.asProperties(
                "annotators", "tokenize,ssplit,pos,lemma,depparse,natlog,openie"
        );
        pipeline = new StanfordCoreNLP(props);

        String serializedClassifier = "edu/stanford/nlp/models/ner/english.all.3class.distsim.crf.ser.gz";
        classifier = CRFClassifier.getClassifier(serializedClassifier);
    }

    public void openIe(String text) {
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);

        // Loop over sentences in the document
        int sentNo = 0;
        for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) {
            System.out.println("Sentence #" + ++sentNo + ": " + sentence.get(CoreAnnotations.TextAnnotation.class));

            Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);

            // Print the triples
            for (RelationTriple triple : triples) {
                System.out.println(triple.confidence + "\t" +
                        triple.subjectGloss() + "\t" +
                        triple.relationGloss() + "\t" +
                        triple.objectGloss());
            }
        }
    }

    public HashMap<String, Set<Set<String>>> ner(String text){

        HashMap<String, Set<Set<String>>> setsByOrganization = new HashMap<>();

        ObjectBank<List<CoreLabel>> lists = classifier.makeObjectBankFromString(text, classifier.plainTextReaderAndWriter());
        for (List<CoreLabel> list : lists) {
            List<CoreLabel> classify = classifier.classify(list);
            String prev = null;
            String combined = null;

            for (CoreLabel coreLabel : classify) {
                String s = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
                if (!"O".equals(s)) {
                    if (s.equals(prev)) {
                        combined += " " + coreLabel.word();
                    } else {
                        prev = s;
                        combined = coreLabel.word();
                    }
                } else {
                    if (combined != null) {
                        if (!setsByOrganization.containsKey(prev)) {
                            setsByOrganization.put(prev, new HashSet<>());
                        }

                        add(combined, setsByOrganization.get(prev));
                    }
                    prev = null;
                    combined = null;
                }
            }
        }

        return setsByOrganization;
    }

    private void add(String name, Set<Set<String>> nameSets) {
        for (Set<String> nameSet : nameSets) {
            for (String s : nameSet) {
                if (name.contains(s) || s.contains(name)){
                    nameSet.add(name);
                    return;
                }
            }
        }

        HashSet<String> newSet = new HashSet<>();
        newSet.add(name);
        nameSets.add(newSet);
    }

}
