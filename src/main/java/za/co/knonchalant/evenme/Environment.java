package za.co.knonchalant.evenme;

public class Environment {
    public String catCookie;
    public String uatCookie;
    public String uidCookie;
    public String chatGPTKey;

    public static Environment fromProperties() {
        Environment e = new Environment();
        e.catCookie = System.getProperty("NEWS24_CAT_COOKIE");
        e.uatCookie = System.getProperty("NEWS24_UAT_COOKIE");
        e.uidCookie = System.getProperty("NEWS24_UID_COOKIE");
        e.chatGPTKey = System.getProperty("CHAT_GPT_KEY");
        return e;
    }

    public static Environment fromHardcode() {
        Environment e = new Environment();
        e.catCookie = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3MjE2MzE2OTMsIm5iZiI6MTcyMTYyNDQ5MywiZXhwIjoxNzIxNjI2Mjg3LCJ1c2VyIjp7ImlkIjoiQUpaNEJCYWM1TFVDVjB0NUFGSXZDSzVxcE81MiIsImVtYWlsIjoiam9obmRldmVuaXNoQGdtYWlsLmNvbSIsInVzZXJuYW1lIjoiam9obmRldmVuaXNoIn19.taIVaGUgX4PCWKs9noonmbTUVn77U0Qqc_OkEFOnvlI";
        e.uatCookie = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjBjYjQyNzQyYWU1OGY0ZGE0NjdiY2RhZWE0Yjk1YTI5ZmJhMGM1ZjkiLCJ0eXAiOiJKV1QifQ.eyJ0Zl9ib29rbWFya3MiOnRydWUsInRmX3RyYWZmaWNfYWxsIjp0cnVlLCJ0Zl93ZWF0aGVyX2FsbCI6dHJ1ZSwidGZfbmV3c2xldHRlcnNfZnJlZSI6dHJ1ZSwidGZfcmVnaXN0ZXJlZCI6dHJ1ZSwidGZfY29tbWVudHMiOnRydWUsInRmX2FydGljbGVfYXVkaW8iOnRydWUsInRmX3BkZiI6dHJ1ZSwidGZfYXJ0aWNsZXMiOnRydWUsInRmX25ld3NsZXR0ZXJzX2FsbCI6dHJ1ZSwidGZfc3Vic2NyaWJlZCI6dHJ1ZSwidGZfdXNlclR5cGUiOjMsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS90d2VudHlmb3VyLWVuZ2xpc2gtbGl2ZSIsImF1ZCI6InR3ZW50eWZvdXItZW5nbGlzaC1saXZlIiwiYXV0aF90aW1lIjoxNzIxMzY2NzEwLCJ1c2VyX2lkIjoiQUpaNEJCYWM1TFVDVjB0NUFGSXZDSzVxcE81MiIsInN1YiI6IkFKWjRCQmFjNUxVQ1YwdDVBRkl2Q0s1cXBPNTIiLCJpYXQiOjE3MjE2MjI2ODcsImV4cCI6MTcyMTYyNjI4NywiZW1haWwiOiJqb2huZGV2ZW5pc2hAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsiam9obmRldmVuaXNoQGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6InBhc3N3b3JkIn19.Z0ttZRR2Li8kIr9MB19bAhrfq6kZO5JOgUOPY7JaHQkbO766BxOHskVWED7x4r6pOTC0tIq7zuMMCk1Sdengd7QlyzjpAOjHsJaiUMjCIEPEAdZ1I40tze3NyUMZRjvprSfNwAo-99AKAkMJvZRPMlyRAB1KNRs_Qtauma3s3vTx3rVVo1CavkbTougH6IDDfeFVv_YPBYtUoC3GV54bmww0aiNdw9aHC2ETQtnKXzMX2EY_4f0V1poVO4nij7IipnuOl5tgjTl_mFKiVnXQR3GkpBjXL5jve7qe4-e5fIBc38fVGryIktZfcWKu8lVSaYlSrRa6e4MXdKTaANLP7g";
        e.uidCookie = "aebcfea5-ad89-4328-9152-46fee42f741f";
        e.chatGPTKey = "CHATGPT";
        return e;
    }

    private Environment() {
    }

}
