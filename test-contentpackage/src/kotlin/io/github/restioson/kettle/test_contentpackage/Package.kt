package io.github.restioson.kettle.test_contentpackage

import io.github.restioson.kettle.api.ClientSidePackage
import io.github.restioson.kettle.api.ContentPackage
import io.github.restioson.kettle.api.Kettle
import io.github.restioson.kettle.api.ServerSidePackage

class Package : ContentPackage {

    override lateinit var engine: Kettle
    override lateinit var clientSide: ClientSidePackage
    override lateinit var serverSide: ServerSidePackage

    override fun create() {
        this.serverSide = SimpleServerSide(this.engine)
        this.clientSide = SimpleClientSide(this.engine, this.serverSide.level, 640f, 360f) // TODO separate server from client
    }

}