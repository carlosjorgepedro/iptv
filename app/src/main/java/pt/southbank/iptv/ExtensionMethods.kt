package pt.southbank.iptv

import java.util.stream.Stream

/**
 * Created by carlos on 16/02/18.
 *
 * Extension methods added as needed
 */

/**
 * Extension method for splitting Streams into batches
 */
fun <T, K> K.split(batchSize: Int): Iterable<Iterable<T>> where K : Stream<T> {
    val batches = mutableListOf<List<T>>()
    var index = 0

    for (item in this) {
        var currentBatch = mutableListOf<T>()
        if (index >= batchSize) {
            batches.add(currentBatch)
            index = 0
            currentBatch = mutableListOf()
        }
        currentBatch.add(item)
        index++
    }

    return batches
}