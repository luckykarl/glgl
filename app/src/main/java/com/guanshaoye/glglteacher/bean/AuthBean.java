package com.guanshaoye.glglteacher.bean;

/**
 * Created by karl on 2017/6/2.
 */

public class AuthBean {

    /**
     * response : {"UploadAddress":"eyJFbmRwb2ludCI6Imh0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6ImluLTIwMTcwNTIyMTA1MDUwODIwLW40d3FtcXl4NjMiLCJGaWxlTmFtZSI6InZpZGVvL0U5QzMyRTYtMTVDNjg0MTkxQzQtMTA4My02ODg0LTQ3Ni01Njk2Ny5tcDQifQ==","VideoId":"786158a789fc4897b7abf650bd059904","RequestId":"F6653919-0499-4BC2-A23D-45AD3C0228A6","UploadAuth":"eyJTZWN1cml0eVRva2VuIjoiQ0FJU3ZBTjFxNkZ0NUIyeWZTaklwdmYvY3UvbnZidGI0YWlnZUZUZXFURTdZY0p1cVpiQmdUejJJSHBLZVhkdUFlQVhzL28wbW1oWjcvWVlsclVxRTg4ZUd4eVVNWmN2c01rSm9GMytKcGZadjh1ODRhQURpNUNqUWZKN2diVlZuNTI4V2Y3d2FmK0FVR25FQ1RtZDVMY2FvOWJUY1RHbFFDYW5VL21nZ29KbWFkSTZSeFN4YVNFOGF2NWRPZ3BscnIwZ1Z4N1dMdTMvQ2dQMnBXRFNBVUYwMkhwN2tuZ3R3SzY0Mk5HNTl4N0NqVVh6MFBNb3Y0RDJLNVRHSDg1M0o4VmJVc3lwK2ZGeWFxdloyVFJNMWdCTzg2NTZsODRoL2l6YzdQV2NHRVZwNmcrYUtKREhrTHRWSVJSK2U3SXhGb05kc2ZINGpvY1lnT0hJa0pudHdCcy9Db05nV2kvRUZvZTcyNW1HU3FIN045QjhMKzZpWkN5UWlJcldic1N0N2x0ME95cGVNbDlWWXQ4NE8yOHVVbHN5U3ozWExlaUtxWHVST0NyOUR2SFkrLzltamNNa2xuNjB1SVBTZHdQS0hlWEpqV05FWmNGNU1oMTVhVUZRMkhTOVVOZGZJMU1YTFF3M1d1N0xGTjh2TkUwQms4Nnk0MTJPREI4SDUycE1vdmp6YThuUnZxMGljb2psVnZwRXE5RkhPc2tZN1RONkhnbXBFT3oyMng1RUxuWVphTEpYd2JUckI1aXowN2FNMjk2UGV0VEJEdjBYUy91dHQ4UTZiVUVhZ0FHaFJhcVZxTjlWU09ibzZQRVcrSFJnUk5DZFRxVHpPN202UlJ0TmV0d1oxVEMrLzM3bzI5SVRzdk5VdUx4YUYvMkRZZ3ozeVlGMEJCZHZEMHpiTERNOVYvS3B6cUErc04wakV3T2hEd2lUTmtYYzNSc2NxUVpWSjZNTGJkNTgrZk0rRFJUandGNnZWTzNRUUJXcTdhOEJwMDdmbE52eDNFNWZZaW5ORTNxNTFBPT0iLCJBY2Nlc3NLZXlJZCI6IlNUUy5HNEo5VVNQZHpWakt6cm9NMW5tTUJGU2pjIiwiQWNjZXNzS2V5U2VjcmV0IjoiOUFEbjZOaTRMcnlHTjJQZmttSk0yZHNDUXp6OUJUWTc3cUhUNlBUQ0RLRkgiLCJFeHBpcmF0aW9uIjoiMzYwMCJ9"}
     */

    private ResponseBean response;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * UploadAddress : eyJFbmRwb2ludCI6Imh0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6ImluLTIwMTcwNTIyMTA1MDUwODIwLW40d3FtcXl4NjMiLCJGaWxlTmFtZSI6InZpZGVvL0U5QzMyRTYtMTVDNjg0MTkxQzQtMTA4My02ODg0LTQ3Ni01Njk2Ny5tcDQifQ==
         * VideoId : 786158a789fc4897b7abf650bd059904
         * RequestId : F6653919-0499-4BC2-A23D-45AD3C0228A6
         * UploadAuth : eyJTZWN1cml0eVRva2VuIjoiQ0FJU3ZBTjFxNkZ0NUIyeWZTaklwdmYvY3UvbnZidGI0YWlnZUZUZXFURTdZY0p1cVpiQmdUejJJSHBLZVhkdUFlQVhzL28wbW1oWjcvWVlsclVxRTg4ZUd4eVVNWmN2c01rSm9GMytKcGZadjh1ODRhQURpNUNqUWZKN2diVlZuNTI4V2Y3d2FmK0FVR25FQ1RtZDVMY2FvOWJUY1RHbFFDYW5VL21nZ29KbWFkSTZSeFN4YVNFOGF2NWRPZ3BscnIwZ1Z4N1dMdTMvQ2dQMnBXRFNBVUYwMkhwN2tuZ3R3SzY0Mk5HNTl4N0NqVVh6MFBNb3Y0RDJLNVRHSDg1M0o4VmJVc3lwK2ZGeWFxdloyVFJNMWdCTzg2NTZsODRoL2l6YzdQV2NHRVZwNmcrYUtKREhrTHRWSVJSK2U3SXhGb05kc2ZINGpvY1lnT0hJa0pudHdCcy9Db05nV2kvRUZvZTcyNW1HU3FIN045QjhMKzZpWkN5UWlJcldic1N0N2x0ME95cGVNbDlWWXQ4NE8yOHVVbHN5U3ozWExlaUtxWHVST0NyOUR2SFkrLzltamNNa2xuNjB1SVBTZHdQS0hlWEpqV05FWmNGNU1oMTVhVUZRMkhTOVVOZGZJMU1YTFF3M1d1N0xGTjh2TkUwQms4Nnk0MTJPREI4SDUycE1vdmp6YThuUnZxMGljb2psVnZwRXE5RkhPc2tZN1RONkhnbXBFT3oyMng1RUxuWVphTEpYd2JUckI1aXowN2FNMjk2UGV0VEJEdjBYUy91dHQ4UTZiVUVhZ0FHaFJhcVZxTjlWU09ibzZQRVcrSFJnUk5DZFRxVHpPN202UlJ0TmV0d1oxVEMrLzM3bzI5SVRzdk5VdUx4YUYvMkRZZ3ozeVlGMEJCZHZEMHpiTERNOVYvS3B6cUErc04wakV3T2hEd2lUTmtYYzNSc2NxUVpWSjZNTGJkNTgrZk0rRFJUandGNnZWTzNRUUJXcTdhOEJwMDdmbE52eDNFNWZZaW5ORTNxNTFBPT0iLCJBY2Nlc3NLZXlJZCI6IlNUUy5HNEo5VVNQZHpWakt6cm9NMW5tTUJGU2pjIiwiQWNjZXNzS2V5U2VjcmV0IjoiOUFEbjZOaTRMcnlHTjJQZmttSk0yZHNDUXp6OUJUWTc3cUhUNlBUQ0RLRkgiLCJFeHBpcmF0aW9uIjoiMzYwMCJ9
         */

        private String UploadAddress;
        private String VideoId;
        private String RequestId;
        private String UploadAuth;

        public String getUploadAddress() {
            return UploadAddress;
        }

        public void setUploadAddress(String UploadAddress) {
            this.UploadAddress = UploadAddress;
        }

        public String getVideoId() {
            return VideoId;
        }

        public void setVideoId(String VideoId) {
            this.VideoId = VideoId;
        }

        public String getRequestId() {
            return RequestId;
        }

        public void setRequestId(String RequestId) {
            this.RequestId = RequestId;
        }

        public String getUploadAuth() {
            return UploadAuth;
        }

        public void setUploadAuth(String UploadAuth) {
            this.UploadAuth = UploadAuth;
        }
    }
}
