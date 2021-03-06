package za.co.knonchalant.evenme;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.objectbank.ObjectBank;
import edu.stanford.nlp.sequences.DocumentReaderAndWriter;
import edu.stanford.nlp.util.Triple;

import java.util.List;


/**
 * This is a demo of calling CRFClassifier programmatically.
 * <p>
 * Usage: {@code java -mx400m -cp "*" NERDemo [serializedClassifier [fileName]] }
 * <p>
 * If arguments aren't specified, they default to
 * classifiers/english.all.3class.distsim.crf.ser.gz and some hardcoded sample text.
 * If run with arguments, it shows some of the ways to get k-best labelings and
 * probabilities out with CRFClassifier. If run without arguments, it shows some of
 * the alternative output formats that you can get.
 * <p>
 * To use CRFClassifier from the command line:
 * </p><blockquote>
 * {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -textFile [file] }
 * </blockquote><p>
 * Or if the file is already tokenized and one word per line, perhaps in
 * a tab-separated value format with extra columns for part-of-speech tag,
 * etc., use the version below (note the 's' instead of the 'x'):
 * </p><blockquote>
 * {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -testFile [file] }
 * </blockquote>
 *
 * @author Jenny Finkel
 * @author Christopher Manning
 */

public class NERDemo {

    public static void main(String[] args) throws Exception {



    }

}