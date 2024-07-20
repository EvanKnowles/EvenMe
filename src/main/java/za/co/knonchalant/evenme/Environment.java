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
        e.catCookie = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3MjE0ODg3NDcsIm5iZiI6MTcyMTQ4MTU0NywiZXhwIjoxNzIxNDg1MTQ1LCJ1c2VyIjp7ImlkIjoiWEpzc0NIeXlIdVgwMUFlRFpjU29OVTkxV1UwMyIsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwidXNlcm5hbWUiOiJFdmFuIEtub3dsZXMifX0.2SJm3B6LlWIo05Aijp-7JuwNZe6EEQ11usMxChaffGk";
        e.uatCookie = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImMxNTQwYWM3MWJiOTJhYTA2OTNjODI3MTkwYWNhYmU1YjA1NWNiZWMiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiRXZhbiBKYW1lcyBLbm93bGVzIiwicGljdHVyZSI6Imh0dHBzOi8vZ3JhcGguZmFjZWJvb2suY29tLzg1NjE0MDExMS9waWN0dXJlIiwidGZfYm9va21hcmtzIjp0cnVlLCJ0Zl90cmFmZmljX2FsbCI6dHJ1ZSwidGZfd2VhdGhlcl9hbGwiOnRydWUsInRmX25ld3NsZXR0ZXJzX2ZyZWUiOnRydWUsInRmX3JlZ2lzdGVyZWQiOnRydWUsInRmX2NvbW1lbnRzIjp0cnVlLCJ0Zl9hcnRpY2xlX2F1ZGlvIjp0cnVlLCJ0Zl9wZGYiOnRydWUsInRmX2FydGljbGVzIjp0cnVlLCJ0Zl9uZXdzbGV0dGVyc19hbGwiOnRydWUsInRmX3N1YnNjcmliZWQiOnRydWUsInRmX3VzZXJUeXBlIjozLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdHdlbnR5Zm91ci1lbmdsaXNoLWxpdmUiLCJhdWQiOiJ0d2VudHlmb3VyLWVuZ2xpc2gtbGl2ZSIsImF1dGhfdGltZSI6MTcyMTI5MjI3MSwidXNlcl9pZCI6IlhKc3NDSHl5SHVYMDFBZURaY1NvTlU5MVdVMDMiLCJzdWIiOiJYSnNzQ0h5eUh1WDAxQWVEWmNTb05VOTFXVTAzIiwiaWF0IjoxNzIxNDgxNTQ1LCJleHAiOjE3MjE0ODUxNDUsImVtYWlsIjoicGlyYXRlYW5nZWxAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsicGlyYXRlYW5nZWxAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.HKw88xksOP3GqUapRx_I-QgUMvCGokJV8nOKoYYn4D_OuXeR2MIvFbhKS8LV8S0qK7jVC8ot_WSsffP1L7TMg9RrtAs1IlVpokLMTDhERcVht_iNI_ppprVEE1NttV4kpGl6whfkfe4Hi2pNDSV-fe9v8MGRwCO0vRJg1ADJGQSmBonP6KGbu3YMxx8xw4Q8myGf9I4wPbh38c_CorafKdPhClIfpGHauCNp7fg2PLVy42lhinCaW4bVmpHrg-HtICM7homldcbxYOeOlaDfm0bSsXTvcQ_SvdMHMeMNdDOipELVa-8BsKBVUk89__VX8X2DVouE3hWlh1vNnChcGQ";
        e.uidCookie = "aebcfea5-ad89-4328-9152-46fee42f741f";
        return e;
    }

    private Environment() {
    }

}
