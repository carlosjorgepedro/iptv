package pt.southbank.iptv

import android.net.Uri
import pt.southbank.library.EpgSyncJobService
import pt.southbank.library.models.Channel
import pt.southbank.library.models.Program
import java.net.URL
import java.util.stream.Stream

/**
 * Created by carlos on 14/02/18.
 *
 * Implementation of JobService that fetches data from
 * m3u and xmltv sources
 */

class IptvJobService : EpgSyncJobService() {
    val configurationProvider = IptvConfigurationProvider()
    val channelBuilder = ChannelBuilder()



    override fun getChannels(): MutableList<Channel> {
        var streamOfDataLines = configurationProvider.M3uUrl
                .openConnection()
                .getInputStream()
                .reader()
                .readLines()
                .stream()
                .split(2)
                .map { x -> channelBuilder.buldChannel(x) }
        return mutableListOf()
    }





    override fun getProgramsForChannel(channelUri: Uri?, channel: Channel?, startMs: Long, endMs: Long): MutableList<Program> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


class IptvConfigurationProvider() {
    val M3uUrl: URL
        get() = URL("http://www.google.com")
}