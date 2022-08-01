package org.hyrical.kitpvp.vault

import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.hyrical.kitpvp.profiles.Profile
import org.hyrical.kitpvp.profiles.getProfile

class Vault : Economy {
    /**
     * Checks if economy method is enabled.
     * @return Success or Failure
     */
    override fun isEnabled(): Boolean {
        return true
    }

    /**
     * Gets name of economy method
     * @return Name of Economy Method
     */
    override fun getName(): String {
        return "VexKits"
    }

    /**
     * Returns true if the given implementation supports banks.
     * @return true if the implementation supports banks
     */
    override fun hasBankSupport(): Boolean {
        return false
    }

    /**
     * Some economy plugins round off after a certain number of digits.
     * This function returns the number of digits the plugin keeps
     * or -1 if no rounding occurs.
     * @return number of digits after the decimal point kept
     */
    override fun fractionalDigits(): Int {
        return 2
    }

    /**
     * Format amount into a human readable String This provides translation into
     * economy specific formatting to improve consistency between plugins.
     *
     * @param amount to format
     * @return Human readable string describing amount
     */
    override fun format(amount: Double): String {
        return amount.toString()
    }

    /**
     * Returns the name of the currency in plural form.
     * If the economy being used does not support currency names then an empty string will be returned.
     *
     * @return name of the currency (plural)
     */
    override fun currencyNamePlural(): String {
        return "Moneys"
    }

    /**
     * Returns the name of the currency in singular form.
     * If the economy being used does not support currency names then an empty string will be returned.
     *
     * @return name of the currency (singular)
     */
    override fun currencyNameSingular(): String {
        return "Money"
    }

    override fun hasAccount(playerName: String?): Boolean {
        return Bukkit.getOfflinePlayer(playerName).hasPlayedBefore()
    }

    /**
     * Checks if this player has an account on the server yet
     * This will always return true if the player has joined the server at least once
     * as all major economy plugins auto-generate a player account when the player joins the server
     *
     * @param player to check
     * @return if the player has an account
     */
    override fun hasAccount(player: OfflinePlayer?): Boolean {
        return player!!.hasPlayedBefore()
    }

    override fun hasAccount(playerName: String?, worldName: String?): Boolean {
        return Bukkit.getOfflinePlayer(playerName).hasPlayedBefore()
    }

    /**
     * Checks if this player has an account on the server yet on the given world
     * This will always return true if the player has joined the server at least once
     * as all major economy plugins auto-generate a player account when the player joins the server
     *
     * @param player to check in the world
     * @param worldName world-specific account
     * @return if the player has an account
     */
    override fun hasAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        return player!!.hasPlayedBefore()
    }

    override fun getBalance(playerName: String?): Double {
        return Bukkit.getOfflinePlayer(playerName).getProfile()!!.balance
    }

    /**
     * Gets balance of a player
     *
     * @param player of the player
     * @return Amount currently held in players account
     */
    override fun getBalance(player: OfflinePlayer?): Double {
        return player!!.getProfile()!!.balance
    }

    override fun getBalance(playerName: String?, world: String?): Double {
        return Bukkit.getOfflinePlayer(playerName).getProfile()!!.balance
    }

    /**
     * Gets balance of a player on the specified world.
     * IMPLEMENTATION SPECIFIC - if an economy plugin does not support this the global balance will be returned.
     * @param player to check
     * @param world name of the world
     * @return Amount currently held in players account
     */
    override fun getBalance(player: OfflinePlayer?, world: String?): Double {
        return player!!.getProfile()!!.balance
    }

    override fun has(playerName: String?, amount: Double): Boolean {
        return Bukkit.getOfflinePlayer(playerName).getProfile()!!.balance >= amount
    }

    /**
     * Checks if the player account has the amount - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param player to check
     * @param amount to check for
     * @return True if **player** has **amount**, False else wise
     */
    override fun has(player: OfflinePlayer?, amount: Double): Boolean {
        return player!!.getProfile()!!.balance >= amount
    }

    override fun has(playerName: String?, worldName: String?, amount: Double): Boolean {
        return Bukkit.getOfflinePlayer(playerName).getProfile()!!.balance >= amount
    }

    /**
     * Checks if the player account has the amount in a given world - DO NOT USE NEGATIVE AMOUNTS
     * IMPLEMENTATION SPECIFIC - if an economy plugin does not support this the global balance will be returned.
     *
     * @param player to check
     * @param worldName to check with
     * @param amount to check for
     * @return True if **player** has **amount**, False else wise
     */
    override fun has(player: OfflinePlayer?, worldName: String?, amount: Double): Boolean {
        return player!!.getProfile()!!.balance >= amount
    }

    override fun withdrawPlayer(playerName: String?, amount: Double): EconomyResponse {
        val player = Bukkit.getOfflinePlayer(playerName)
        if (player.getProfile()!!.balance < amount) {
            return EconomyResponse(0.0, player.getProfile()!!.balance, EconomyResponse.ResponseType.FAILURE, "Insufficient funds")
        }
        player.getProfile().also {
            it!!.balance -= amount
            it!!.save()
        }

        return EconomyResponse(amount, player.getProfile()!!.balance, EconomyResponse.ResponseType.SUCCESS, null)
    }

    /**
     * Withdraw an amount from a player - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param player to withdraw from
     * @param amount Amount to withdraw
     * @return Detailed response of transaction
     */
    override fun withdrawPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        if (player!!.getProfile()!!.balance < amount) {
            return EconomyResponse(0.0, player.getProfile()!!.balance, EconomyResponse.ResponseType.FAILURE, "Insufficient funds")
        }
        player.getProfile().also {
            it!!.balance -= amount
            it!!.save()
        }

        return EconomyResponse(amount, player.getProfile()!!.balance, EconomyResponse.ResponseType.SUCCESS, null)
    }

    override fun withdrawPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        val player = Bukkit.getOfflinePlayer(playerName)
        if (player.getProfile()!!.balance < amount) {
            return EconomyResponse(0.0, player.getProfile()!!.balance, EconomyResponse.ResponseType.FAILURE, "Insufficient funds")
        }
        player.getProfile().also {
            it!!.balance -= amount
            it!!.save()
        }

        return EconomyResponse(amount, player.getProfile()!!.balance, EconomyResponse.ResponseType.SUCCESS, null)
    }

    /**
     * Withdraw an amount from a player on a given world - DO NOT USE NEGATIVE AMOUNTS
     * IMPLEMENTATION SPECIFIC - if an economy plugin does not support this the global balance will be returned.
     * @param player to withdraw from
     * @param worldName - name of the world
     * @param amount Amount to withdraw
     * @return Detailed response of transaction
     */
    override fun withdrawPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        if (player!!.getProfile()!!.balance < amount) {
            return EconomyResponse(0.0, player.getProfile()!!.balance, EconomyResponse.ResponseType.FAILURE, "Insufficient funds")
        }
        player.getProfile().also {
            it!!.balance -= amount
            it!!.save()
        }

        return EconomyResponse(amount, player.getProfile()!!.balance, EconomyResponse.ResponseType.SUCCESS, null)
    }

    override fun depositPlayer(playerName: String?, amount: Double): EconomyResponse {
        val player = Bukkit.getOfflinePlayer(playerName)
        player.getProfile().also {
            it!!.balance += amount
            it!!.save()
        }

        return EconomyResponse(amount, player.getProfile()!!.balance, EconomyResponse.ResponseType.SUCCESS, null)
    }

    /**
     * Deposit an amount to a player - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param player to deposit to
     * @param amount Amount to deposit
     * @return Detailed response of transaction
     */
    override fun depositPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        player!!.getProfile().also {
            it!!.balance += amount
            it!!.save()
        }
        return EconomyResponse(amount, player.getProfile()!!.balance, EconomyResponse.ResponseType.SUCCESS, null)
    }

    override fun depositPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        val player = Bukkit.getOfflinePlayer(playerName)
        player.getProfile().also {
            it!!.balance += amount
            it!!.save()
        }
        return EconomyResponse(amount, player.getProfile()!!.balance, EconomyResponse.ResponseType.SUCCESS, null)
    }

    /**
     * Deposit an amount to a player - DO NOT USE NEGATIVE AMOUNTS
     * IMPLEMENTATION SPECIFIC - if an economy plugin does not support this the global balance will be returned.
     *
     * @param player to deposit to
     * @param worldName name of the world
     * @param amount Amount to deposit
     * @return Detailed response of transaction
     */
    override fun depositPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        player!!.getProfile().also {
            it!!.balance += amount
            it!!.save()
        }
        return EconomyResponse(amount, player.getProfile()!!.balance, EconomyResponse.ResponseType.SUCCESS, null)
    }

    override fun createBank(name: String?, player: String?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported")
    }

    /**
     * Creates a bank account with the specified name and the player as the owner
     * @param name of account
     * @param player the account should be linked to
     * @return EconomyResponse Object
     */
    override fun createBank(name: String?, player: OfflinePlayer?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported")
    }

    /**
     * Deletes a bank account with the specified name.
     * @param name of the back to delete
     * @return if the operation completed successfully
     */
    override fun deleteBank(name: String?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported")
    }

    /**
     * Returns the amount the bank has
     * @param name of the account
     * @return EconomyResponse Object
     */
    override fun bankBalance(name: String?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported")
    }

    /**
     * Returns true or false whether the bank has the amount specified - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param name of the account
     * @param amount to check for
     * @return EconomyResponse Object
     */
    override fun bankHas(name: String?, amount: Double): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported")
    }

    /**
     * Withdraw an amount from a bank account - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param name of the account
     * @param amount to withdraw
     * @return EconomyResponse Object
     */
    override fun bankWithdraw(name: String?, amount: Double): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported")
    }

    /**
     * Deposit an amount into a bank account - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param name of the account
     * @param amount to deposit
     * @return EconomyResponse Object
     */
    override fun bankDeposit(name: String?, amount: Double): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported")
    }

    override fun isBankOwner(name: String?, playerName: String?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported")
    }

    /**
     * Check if a player is the owner of a bank account
     *
     * @param name of the account
     * @param player to check for ownership
     * @return EconomyResponse Object
     */
    override fun isBankOwner(name: String?, player: OfflinePlayer?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported")
    }

    override fun isBankMember(name: String?, playerName: String?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported")
    }

    /**
     * Check if the player is a member of the bank account
     *
     * @param name of the account
     * @param player to check membership
     * @return EconomyResponse Object
     */
    override fun isBankMember(name: String?, player: OfflinePlayer?): EconomyResponse {
        return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Banks are not supported")
    }

    /**
     * Gets the list of banks
     * @return the List of Banks
     */
    override fun getBanks(): MutableList<String> {
        return mutableListOf()
    }

    override fun createPlayerAccount(playerName: String?): Boolean {
        if (Bukkit.getOfflinePlayer(playerName).hasPlayedBefore()) return false

        val profile = Profile(Bukkit.getOfflinePlayer(playerName).uniqueId)
        profile.save()

        return true
    }

    /**
     * Attempts to create a player account for the given player
     * @param player OfflinePlayer
     * @return if the account creation was successful
     */
    override fun createPlayerAccount(player: OfflinePlayer?): Boolean {
        if (player!!.hasPlayedBefore()) return false

        val profile = Profile(player.uniqueId)
        profile.save()

        return true
    }

    override fun createPlayerAccount(playerName: String?, worldName: String?): Boolean {
        if (Bukkit.getOfflinePlayer(playerName).hasPlayedBefore()) return false

        val profile = Profile(Bukkit.getOfflinePlayer(playerName).uniqueId)
        profile.save()

        return true
    }

    /**
     * Attempts to create a player account for the given player on the specified world
     * IMPLEMENTATION SPECIFIC - if an economy plugin does not support this then false will always be returned.
     * @param player OfflinePlayer
     * @param worldName String name of the world
     * @return if the account creation was successful
     */
    override fun createPlayerAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        if (player!!.hasPlayedBefore()) return false

        val profile = Profile(player.uniqueId)
        profile.save()

        return true
    }
}