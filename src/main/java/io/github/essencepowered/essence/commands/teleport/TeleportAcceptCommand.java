/*
 * This file is part of Essence, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package io.github.essencepowered.essence.commands.teleport;

import com.google.inject.Inject;
import io.github.essencepowered.essence.Util;
import io.github.essencepowered.essence.api.PluginModule;
import io.github.essencepowered.essence.internal.CommandBase;
import io.github.essencepowered.essence.internal.annotations.Modules;
import io.github.essencepowered.essence.internal.annotations.NoCooldown;
import io.github.essencepowered.essence.internal.annotations.NoCost;
import io.github.essencepowered.essence.internal.annotations.NoWarmup;
import io.github.essencepowered.essence.internal.annotations.Permissions;
import io.github.essencepowered.essence.internal.annotations.RegisterCommand;
import io.github.essencepowered.essence.internal.permissions.SuggestedLevel;
import io.github.essencepowered.essence.internal.services.TeleportHandler;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;

/**
 * /tpaccept.
 */
@Modules(PluginModule.TELEPORT)
@Permissions(root = "teleport", suggestedLevel = SuggestedLevel.USER)
@NoWarmup
@NoCooldown
@NoCost
@RegisterCommand({"tpaccept", "teleportaccept"})
public class TeleportAcceptCommand extends CommandBase<Player> {

    @Inject private TeleportHandler teleportHandler;

    @Override
    public CommandSpec createSpec() {
        return CommandSpec.builder().executor(this).build();
    }

    @Override
    public CommandResult executeCommand(Player src, CommandContext args) throws Exception {
        if (teleportHandler.getAndExecute(src.getUniqueId())) {
            src.sendMessage(Util.getTextMessageWithFormat("command.tpaccept.success"));
            return CommandResult.success();
        }

        src.sendMessage(Util.getTextMessageWithFormat("command.tpaccept.nothing"));
        return CommandResult.empty();
    }
}
