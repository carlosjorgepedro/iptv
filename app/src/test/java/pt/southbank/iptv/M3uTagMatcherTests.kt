package pt.southbank.iptv

import junit.framework.Assert
import org.junit.Test

/**
 * Created by carlos on 17/02/18.
 *
 * Tests for channel builder class
 */

class M3uTagMatcherTests {
    private val tagMatcher = M3uTagMatcher()
    private val sampleMetaData = "#EXTINF:-1 tvg-ID=\"ITV1London.uk\" tvg-name=\"UK: ITV HD\" tvg-logo=\"http://www.tv-logo.com/pt-data/uploads/images/logo/itv_uk_hd.jpg\" group-title=\"UK Entertainment\",UK: ITV HD"

    @Test
    fun matchChannelId() {
        val tagValue = tagMatcher.matchTvId(sampleMetaData)
        Assert.assertNotNull(tagValue)
        Assert.assertEquals("ITV1London.uk", tagValue)
    }

    @Test
    fun noMatchForChannelId() {
        val tagValue = tagMatcher.matchTvId("\"failing\"=tvg-Id")
        Assert.assertNull(tagValue)
    }

    @Test
    fun matchChannelName() {
        val tagValue = tagMatcher.matchChannelName(sampleMetaData)
        Assert.assertNotNull(tagValue)
        Assert.assertEquals("UK: ITV HD", tagValue)
    }

    @Test
    fun matchChannelLogo() {
        val tagValue = tagMatcher.matchChannelLogo(sampleMetaData)
        Assert.assertNotNull(tagValue)
        Assert.assertEquals("http://www.tv-logo.com/pt-data/uploads/images/logo/itv_uk_hd.jpg", tagValue)
    }

    @Test
    fun matchChannelGroup() {
        val tagValue = tagMatcher.matchChannelGroup(sampleMetaData)
        Assert.assertNotNull(tagValue)
        Assert.assertEquals("UK Entertainment", tagValue)
    }

    @Test
    fun matchFalloutChannelData() {
        val tagValue = tagMatcher.matchTitle(sampleMetaData)
        Assert.assertEquals("UK: ITV HD", tagValue)
    }
}