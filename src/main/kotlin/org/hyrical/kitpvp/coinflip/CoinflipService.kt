package org.hyrical.kitpvp.coinflip

import java.util.UUID

object CoinflipService {
    val coinflips = hashMapOf<UUID, Coinflip>()

    data class Coinflip(val player: UUID, val amount: Double, val heads: Boolean)
}