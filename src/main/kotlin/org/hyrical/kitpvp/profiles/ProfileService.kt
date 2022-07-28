package org.hyrical.kitpvp.profiles

import io.github.nosequel.data.DataStoreType
import org.hyrical.kitpvp.KitPvP
import java.util.UUID

object ProfileService {

    val service = KitPvP.instance.dataHandler.createStoreType<UUID, Profile>(DataStoreType.MONGO)

    val cache = HashMap<UUID, Profile>()

}