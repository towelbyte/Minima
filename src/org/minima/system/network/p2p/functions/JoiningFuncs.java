package org.minima.system.network.p2p.functions;

import lombok.extern.slf4j.Slf4j;
import org.minima.system.network.base.MinimaClient;
import org.minima.system.network.p2p.*;
import org.minima.system.network.p2p.messages.ExpiringMessage;
import org.minima.system.network.p2p.messages.P2PMsgWalkLinks;
import org.minima.utils.messages.Message;

import java.net.InetSocketAddress;
import java.util.ArrayList;

@Slf4j
public class JoiningFuncs {
    public static ArrayList<Message> joinRendezvousNode(P2PState state, ArrayList<MinimaClient> clients, Traceable traceable)
    {
        ArrayList<Message> msgs = new ArrayList<>();
        if (!state.getRecentJoinersCopy().isEmpty()) {
            InetSocketAddress address = UtilFuncs.SelectRandomAddress(new ArrayList<>(state.getRecentJoinersCopy()));
            msgs.add(new Message(P2PMessageProcessor.P2P_CONNECT, traceable).addObject("address", address).addString("reason", "RENDEZVOUS connection"));
            state.getConnectionDetailsMap().put(address, new ConnectionDetails(ConnectionReason.RENDEZVOUS));
        } else {
            log.error("No nodes to Rendezvous with - RandomNodeSet is empty");
        }
        return msgs;
    }

    public static ArrayList<Message> joinEntryNode(P2PState state, ArrayList<MinimaClient> clients, Traceable traceable)
    {
        ArrayList<Message> msgs = new ArrayList<>();
        if (!state.getRecentJoinersCopy().isEmpty()) {
            InetSocketAddress address = UtilFuncs.SelectRandomAddress(new ArrayList<>(state.getRecentJoinersCopy()));
            msgs.add(new Message(P2PMessageProcessor.P2P_CONNECT, traceable).addObject("address", address).addString("reason", "ENTRY_NODE connection"));
            state.getConnectionDetailsMap().put(address, new ConnectionDetails(ConnectionReason.ENTRY_NODE));
            state.entryNodeConnected(traceable);
        } else {
            log.error("No nodes to Rendezvous with - RandomNodeSet is empty");
        }

        return msgs;
    }

    public static ArrayList<Message> joinScaleOutLinks(P2PState state, ArrayList<MinimaClient> clients, Traceable traceable)
    {
        ArrayList<Message> msgs = new ArrayList<>();
        if(state.getOutLinksCopy().size() < state.getNumLinks()) {
            int numActiveWalks = countActiveWalkLinks(state, true, true);
            boolean createWalk = state.getOutLinksCopy().size() - numActiveWalks > 0;
            if (createWalk) {
                P2PMsgWalkLinks walkLinks = new P2PMsgWalkLinks(true, true, traceable);
                walkLinks.addHopToPath(state.getAddress());
                InetSocketAddress nextHop = UtilFuncs.SelectRandomAddress(state.getOutLinksCopy());
                MinimaClient minimaClient = UtilFuncs.getClientForInetAddressEitherDirection(nextHop, clients);
                msgs.add(WalkLinksFuncs.genP2PWalkLinkMsg(state, minimaClient, walkLinks, "P2P_JOIN", traceable));
            }
        }
        return msgs;
    }

    public static ArrayList<Message> joinScaleInLinks(P2PState state, ArrayList<MinimaClient> clients, Traceable traceable)
    {
        ArrayList<Message> msgs = new ArrayList<>();
        if(state.getInLinksCopy().size() < state.getNumLinks()) {
            int numActiveWalks = countActiveWalkLinks(state, false, false);
            boolean createWalk = state.getInLinksCopy().size() - numActiveWalks > 0;
            if (createWalk) {
                // Replace Inlink
                P2PMsgWalkLinks walkLinks = new P2PMsgWalkLinks(false, false, traceable);
                walkLinks.addHopToPath(state.getAddress());
                InetSocketAddress nextHop = UtilFuncs.SelectRandomAddress(state.getOutLinksCopy());
                MinimaClient minimaClient = UtilFuncs.getClientForInetAddressEitherDirection(nextHop, clients);
                msgs.add(WalkLinksFuncs.genP2PWalkLinkMsg(state, minimaClient, walkLinks, "P2P_JOIN", traceable));
            }
        }
        return msgs;
    }
    public static int countActiveWalkLinks(P2PState state, boolean isInLinksWalk, boolean isJoiningWalk){
        int numActiveMessages = 0;
        for(ExpiringMessage expiringMessage: state.getExpiringMessageMapCopy().values()){
            Message message = expiringMessage.getMsg();
            if (message.isMessageType(P2PMessageProcessor.P2P_WALK_LINKS)) {
                P2PMsgWalkLinks walkLinks = (P2PMsgWalkLinks) message.getObject("data");
                if (walkLinks.isWalkInLinks() == isInLinksWalk && walkLinks.isJoiningWalk() == isJoiningWalk) {
                    numActiveMessages += 1;
                }
            }
        }
        return numActiveMessages;
    }
}
