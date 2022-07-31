package org.hyrical.kitpvp

import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.connection.flatfile.FlatfileConnectionPool
import io.github.nosequel.data.connection.mongo.MongoConnectionPool
import me.activated.core.plugin.AquaCoreAPI
import net.evilblock.cubed.command.CommandHandler
import net.evilblock.cubed.scoreboard.ScoreboardHandler
import net.evilblock.cubed.util.bukkit.Tasks
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.hyrical.kitpvp.announcer.Announcer
import org.hyrical.kitpvp.buycraft.command.GemCommands
import org.hyrical.kitpvp.buycraft.command.StoreCommand
import org.hyrical.kitpvp.combat.CombatTagHandler
import org.hyrical.kitpvp.commands.*
import org.hyrical.kitpvp.commands.leaderboard.LeaderboardCommand
import org.hyrical.kitpvp.commands.leaderboard.LeaderboardType
import org.hyrical.kitpvp.commands.leaderboard.type.LeaderboardTypeParam
import org.hyrical.kitpvp.kits.Kit
import org.hyrical.kitpvp.kits.KitsService
import org.hyrical.kitpvp.kits.command.KitsCommand
import org.hyrical.kitpvp.koth.commands.KothCommands
import org.hyrical.kitpvp.koth.koth.Koth
import org.hyrical.kitpvp.koth.koth.listener.BowPreventionListener
import org.hyrical.kitpvp.koth.storage.KothHandler
import org.hyrical.kitpvp.leaderboard.DeathLeaderboard
import org.hyrical.kitpvp.leaderboard.KillLeaderboard
import org.hyrical.kitpvp.listeners.*
import org.hyrical.kitpvp.mongo.MongoURIConnection
import org.hyrical.kitpvp.nametag.NameTagProvider
import org.hyrical.kitpvp.profiles.ProfileService
import org.hyrical.kitpvp.profiles.listener.ProfileListener
import org.hyrical.kitpvp.scoreboard.ScoreboardConfig
import org.hyrical.kitpvp.scoreboard.ScoreboardProvider
import org.hyrical.kitpvp.scoreboard.animation.type.LinkAnimation
import org.hyrical.kitpvp.scoreboard.animation.type.TitleAnimation
import org.hyrical.kitpvp.spigot.BowBoostListener
import kotlin.random.Random

class KitPvP : JavaPlugin() {

    companion object {
        lateinit var instance: KitPvP
        val random: Random = Random
    }

    lateinit var mongoConnectionPool: MongoConnectionPool

    lateinit var dataHandler: DataHandler

    override fun onEnable() {
        instance = this
        saveDefaultConfig()

        mongoConnectionPool = MongoURIConnection().apply {
            mongoURI = "mongodb+srv://Nopox:Ln06dNzjDL4j07bZ@cluster0.sftonqr.mongodb.net/?retryWrites=true&w=majority"
            databaseName = "VexKits"
        }

        dataHandler = DataHandler
            .linkTypeToId<Koth>("koth")
            .linkTypeToId<Kit>("kits")
            .withConnectionPool<FlatfileConnectionPool> {
                this.directory = dataFolder.absolutePath
            }.withConnectionPool(mongoConnectionPool)

        ProfileService.service.load()
        KitsService.handler.load()
        KitsService.load()
        KothHandler.load()

        ScoreboardConfig.load()
        Tasks.asyncTimer(ScoreboardProvider.Title, 2L, 2L)
        Tasks.asyncTimer(TitleAnimation, 2L, 2L)
        Tasks.asyncTimer(LinkAnimation, 2L, 2L)

        ScoreboardHandler.configure(ScoreboardProvider.Title, ScoreboardProvider.Scores)

        CommandHandler.registerClass(KitsCommand.javaClass)
        CommandHandler.registerClass(ToggleDeathMessageCommand.javaClass)
        CommandHandler.registerClass(ReloadCommand.javaClass)
        CommandHandler.registerClass(StatsCommand.javaClass)
        CommandHandler.registerClass(BountyCommand.javaClass)
        CommandHandler.registerClass(BalanceCommand.javaClass)
        CommandHandler.registerClass(SpawnCommand.javaClass)
        CommandHandler.registerClass(LeaderboardCommand.javaClass)
        CommandHandler.registerClass(RepairCommand.javaClass)
        CommandHandler.registerClass(RulesCommand.javaClass)
        CommandHandler.registerClass(AdCommand.javaClass)
        CommandHandler.registerClass(StoreCommand.javaClass)
        CommandHandler.registerClass(GemCommands.javaClass)

        CommandHandler.registerClass(KothCommands.javaClass)

        CommandHandler.registerParameterType(LeaderboardType::class.java, LeaderboardTypeParam())

        server.pluginManager.registerEvents(ProfileListener(), this)
        server.pluginManager.registerEvents(DeathMessageListener(), this)
        server.pluginManager.registerEvents(JoinQuitListeners(), this)
        server.pluginManager.registerEvents(KillstreakListener(), this)
        server.pluginManager.registerEvents(CombatTagHandler, this)
        server.pluginManager.registerEvents(GodAppleListener, this)
        server.pluginManager.registerEvents(BountyListener(), this)
        server.pluginManager.registerEvents(BowBoostListener(), this)
        server.pluginManager.registerEvents(ChatListener(), this)
        server.pluginManager.registerEvents(BowPreventionListener(), this)

        NameTagProvider.runTaskTimer(this, 0L, 60L)

        Announcer.load(config)

        KillLeaderboard.load()
        DeathLeaderboard.load()
    }


}

fun translate(s: String): String {
    return ChatColor.translateAlternateColorCodes('&', s)
}

infix fun Player.sendMessage(s: String) {
    this.sendMessage(translate(s))
}

fun Player.getColoredName(): String {
    return AquaCoreAPI.INSTANCE.getPlayerData(uniqueId).highestRank.color.toString() + player.name
}