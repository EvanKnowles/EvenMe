package za.co.knonchalant.evenme;

public class Environment {
    public String catCookie;
    public String uatCookie;
    public String uidCookie;

    public static Environment fromProperties() {
        Environment e = new Environment();
        e.catCookie = System.getProperty("NEWS24_CAT_COOKIE");
        e.uatCookie = System.getProperty("NEWS24_UAT_COOKIE");
        e.uidCookie = System.getProperty("NEWS24_UID_COOKIE");
        return e;
    }

    public static Environment fromHardcode() {
        Environment e = new Environment();
        e.catCookie = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3MjEzOTcwNzEsIm5iZiI6MTcyMTM4OTg3MSwiZXhwIjoxNzIxMzkzNDY4LCJ1c2VyIjp7ImlkIjoiWEpzc0NIeXlIdVgwMUFlRFpjU29OVTkxV1UwMyIsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwidXNlcm5hbWUiOiJFdmFuIEtub3dsZXMifX0.iCmiJTFuD8YakdZyrfuE7zpF7E1En5F41Kkq3CdXm64";
        e.uatCookie = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImMxNTQwYWM3MWJiOTJhYTA2OTNjODI3MTkwYWNhYmU1YjA1NWNiZWMiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRXZhbiBKYW1lcyBLbm93bGVzIiwicGljdHVyZSI6Imh0dHBzOi8vZ3JhcGguZmFjZWJvb2suY29tLzg1NjE0MDExMS9waWN0dXJlIiwidGZfYm9va21hcmtzIjp0cnVlLCJ0Zl90cmFmZmljX2FsbCI6dHJ1ZSwidGZfd2VhdGhlcl9hbGwiOnRydWUsInRmX25ld3NsZXR0ZXJzX2ZyZWUiOnRydWUsInRmX3JlZ2lzdGVyZWQiOnRydWUsInRmX2NvbW1lbnRzIjp0cnVlLCJ0Zl9hcnRpY2xlX2F1ZGlvIjp0cnVlLCJ0Zl9wZGYiOnRydWUsInRmX2FydGljbGVzIjp0cnVlLCJ0Zl9uZXdzbGV0dGVyc19hbGwiOnRydWUsInRmX3N1YnNjcmliZWQiOnRydWUsInRmX3VzZXJUeXBlIjozLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdHdlbnR5Zm91ci1lbmdsaXNoLWxpdmUiLCJhdWQiOiJ0d2VudHlmb3VyLWVuZ2xpc2gtbGl2ZSIsImF1dGhfdGltZSI6MTcyMTI5MjI3MSwidXNlcl9pZCI6IlhKc3NDSHl5SHVYMDFBZURaY1NvTlU5MVdVMDMiLCJzdWIiOiJYSnNzQ0h5eUh1WDAxQWVEWmNTb05VOTFXVTAzIiwiaWF0IjoxNzIxMzg5ODY4LCJleHAiOjE3MjEzOTM0NjgsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsicGlyYXRlYW5nZWxAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.N85UdghdDj4oXurol8S7MZUIWGAt4RIli7ccjprBHVsBt0aIC4OCdkq7p74chaaoH1L8oFbYDsFEBbFAyjYYCOE_wvEL2s57V3Z6URR8az4AS-31dvkC4d42toBLq7Q_trN6xKe5nn5HiInfJwdGDjM0HdkAw4k-ni9flJMZygCdHNRJR0iELlXSoQuy-cw63s2Fsb7P6Ntso3TZFn4L1pN-utHbHQrqmqaKGLdvwsB1efmfgAZ1QGJFgm2KJmVjt5gUwDBmhXsSeKIk9Ov8faAjAE9ECmjPFjI-081yFA85ZKdb9-eTWQtlp49OM8kJq8AtK40KjxdFCUqy3aFCeQ";
        e.uidCookie = "aebcfea5-ad89-4328-9152-46fee42f741f";
        return e;
    }

    private Environment() {
    }

}
