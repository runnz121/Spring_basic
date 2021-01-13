package com.msystech.clone_hospital.api;

import okhttp3.MediaType;

public class NHNToastCommon {
    public static final MediaType MEDIA_JSON = MediaType.parse("application/json; charset=utf-8"); // okhttp 참고 https://digitalbourgeois.tistory.com/59?category=678387

    public static final String TENANTID = "486d2f5889e14dccbc07d026695ecc00";

    public static final String ADDRESS_COMPUTE_LEGION = "https://kr1-api-instance.infrastructure.cloud.toast.com";

    public static final String ADDRESS_TOKEN_ISSUE = "https://api-identity.infrastructure.cloud.toast.com/v2.0/tokens";

    public static final String ADDRESS_NETWORK = "https://kr1-api-network.infrastructure.cloud.toast.com";

    public static final String ADDRESS_BLOCKCHAIN = "http://133.186.208.16:4000";

    public static final String BLOCKCHAIN_ROOT_ORG = "msystech";

    public static final String BLOCKCHAIN_USERID = "admin@msystech";


}

