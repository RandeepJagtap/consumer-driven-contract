import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should add person and should return id=1"

    request {
        url "/person"
        method POST()
        headers {
            contentType applicationJson()
        }
        body (
                id: "1",
                name: "TestName",
                address: "TestAddress"
        )
    }

    response {
        status 202
    }
}