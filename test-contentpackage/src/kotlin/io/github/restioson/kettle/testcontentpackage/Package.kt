package io.github.restioson.kettle.testcontentpackage

import io.github.restioson.kettle.api.ClientSidePackage
import io.github.restioson.kettle.api.ContentPackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.ServerSidePackage

class Package : ContentPackage {

    override lateinit var engine: Kettle
    override lateinit var clientSide: ClientSidePackage
    override lateinit var serverSide: ServerSidePackage

    override fun initClient() {
        this.clientSide = TestClientSide(this.engine, this.serverSide.level as TestLevel, 640f, 360f)
        this.clientSide.init()
    }

    override fun create() {
        this.serverSide = TestServerSide(this.engine)
    }

}