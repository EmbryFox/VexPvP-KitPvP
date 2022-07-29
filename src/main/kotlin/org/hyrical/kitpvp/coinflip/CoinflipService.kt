package org.hyrical.kitpvp.coinflip

import java.util.UUID

class CoinflipService {
    val coinflip = hashMapOf<UUID, Coinflip>()

    data class Coinflip(val player: UUID, val amount: Double, val heads: Boolean)
}