package pt.southbank.iptv

import org.intellij.lang.annotations.RegExp

/**
 * Created by carlos on 17/02/18.
 *
 * Class responsible for finding tag values in m3u strings
 */

class M3uTagMatcher {
    fun matchTvId(value: String): String? {
        return matchRegex("tvg-ID=\"(.+?)\"", value)
    }

    fun matchChannelName(value: String): String? {
        return matchRegex("tvg-name=\"(.+?)\"", value)
    }

    private fun matchRegex(pattern: String, value: String): String? {
        val regex = Regex(pattern)
        val match = regex.find(value) ?: return null
        return when {
            match.groups[1] == null -> null
            else -> match.groups[1]!!.value
        }
    }

    fun matchChannelLogo(value: String): String? {
        return matchRegex("tvg-logo=\"(.+?)\"", value)
    }

    fun matchChannelGroup(value: String): String? {
        return matchRegex("group-title=\"(.+?)\"", value)
    }

    fun matchTitle(value: String): String? {
        return matchRegex(",(.+)$", value)
    }
}