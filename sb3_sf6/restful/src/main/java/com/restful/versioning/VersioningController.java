package com.restful.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
    @GetMapping(path = "/v1/person")
    public PersonV1 getV1Person() {
        return new PersonV1("Single name");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 getV2Person() {
        return new PersonV2(new Name("Double", "Name"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getParams1Person() {
        return new PersonV1("Single name");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getParams2Person() {
        return new PersonV2(new Name("Double", "Name"));
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=1")
    public PersonV1 getHeader1Person() {
        return new PersonV1("Single name");
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getHeader2Person() {
        return new PersonV2(new Name("Double", "Name"));
    }

    //  + v1 & v2 in accept header
}
