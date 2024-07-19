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
        e.catCookie = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3MjEzOTMwMTgsIm5iZiI6MTcyMTM4NTgxOCwiZXhwIjoxNzIxMzg5NDE0LCJ1c2VyIjp7ImlkIjoiQUpaNEJCYWM1TFVDVjB0NUFGSXZDSzVxcE81MiIsImVtYWlsIjoiam9obmRldmVuaXNoQGdtYWlsLmNvbSIsInVzZXJuYW1lIjoiam9obmRldmVuaXNoIn19.B_FLJ7zGONfC2Ppkhqe_ofGq5CMLzgmZBNEbhdnu9Us";
        e.uatCookie = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImMxNTQwYWM3MWJiOTJhYTA2OTNjODI3MTkwYWNhYmU1YjA1NWNiZWMiLCJ0eXAiOiJKV1QifQ.eyJ0Zl9ib29rbWFya3MiOnRydWUsInRmX3RyYWZmaWNfYWxsIjp0cnVlLCJ0Zl93ZWF0aGVyX2FsbCI6dHJ1ZSwidGZfbmV3c2xldHRlcnNfZnJlZSI6dHJ1ZSwidGZfcmVnaXN0ZXJlZCI6dHJ1ZSwidGZfY29tbWVudHMiOnRydWUsInRmX2FydGljbGVfYXVkaW8iOnRydWUsInRmX3BkZiI6dHJ1ZSwidGZfYXJ0aWNsZXMiOnRydWUsInRmX25ld3NsZXR0ZXJzX2FsbCI6dHJ1ZSwidGZfc3Vic2NyaWJlZCI6dHJ1ZSwidGZfdXNlclR5cGUiOjMsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS90d2VudHlmb3VyLWVuZ2xpc2gtbGl2ZSIsImF1ZCI6InR3ZW50eWZvdXItZW5nbGlzaC1saXZlIiwiYXV0aF90aW1lIjoxNzIxMzY2NzEwLCJ1c2VyX2lkIjoiQUpaNEJCYWM1TFVDVjB0NUFGSXZDSzVxcE81MiIsInN1YiI6IkFKWjRCQmFjNUxVQ1YwdDVBRkl2Q0s1cXBPNTIiLCJpYXQiOjE3MjEzODU4MTQsImV4cCI6MTcyMTM4OTQxNCwiZW1haWwiOiJqb2huZGV2ZW5pc2hAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsiam9obmRldmVuaXNoQGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6InBhc3N3b3JkIn19.mDLv1DIyQl3I9_YbxWP0ZpamyvNlXOsfcWYTE-sGLrxSkB7LWR7aHVtHx4vKku2IJ-jLBZ8d9d0YinCZuGFgKJ9BjELua9onHqUHMx8UIeWI_xcAfXvyk2RqfkUHbm6y2oTs2IYTSwDkitlLpf8PLNBOyfJBTrapc5-NpD6UJxzw0G5skDgD-VJ3qDeiJllxNnPVDH4HDN-KIL9NYOQ4YaC8Bz-jbT3A-MLzouEaGM68fJw22bDJ8-q-VPd8AnAFSg7bWm6XendmF7sQIkxe-yzuOqzrWt0IUpAMTzDfvu7NWLbJv5aXd7Z5vtUyBZ-s8WzzItF39aX_eyo8XLZ-fA";
        e.uidCookie = "aebcfea5-ad89-4328-9152-46fee42f741f";
        return e;
    }

    private Environment() {
    }

}
