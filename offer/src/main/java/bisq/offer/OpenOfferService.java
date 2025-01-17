/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.offer;

import bisq.account.settlement.FiatSettlement;
import bisq.common.monetary.Coin;
import bisq.common.monetary.Fiat;
import bisq.network.NetworkId;
import bisq.network.p2p.INetworkService;
import bisq.network.p2p.node.Address;
import bisq.network.p2p.node.transport.Transport;
import bisq.offer.protocol.SwapProtocolType;
import bisq.security.PubKey;
import bisq.wallets.Wallet;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArraySet;

public class OpenOfferService {
    // Expected dependency for deactivating offers if not sufficient wallet balance
    Wallet wallet;

    private final INetworkService networkService;
    private final Set<OpenOffer> openOffers = new CopyOnWriteArraySet<>();

    public OpenOfferService(INetworkService networkService) {
        this.networkService = networkService;
    }

    public CompletableFuture<Boolean> initialize() {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        //todo
        future.complete(true);
        return future;
    }

    public void createNewOffer(long askAmount) {
        Map<Transport.Type, Address> map = Map.of(Transport.Type.CLEAR, Address.localHost(3333));
        NetworkId makerNetworkId = new NetworkId(map, new PubKey(null, "default"), "default");
        SwapSide askSwapSide = new SwapSide(Coin.asBtc(askAmount), List.of());
        SwapSide bidSwapSide = new SwapSide(Fiat.of(5000, "USD"), List.of(FiatSettlement.ZELLE));
        SwapOffer offer = new SwapOffer(bidSwapSide,
                askSwapSide,
                "USD",
                List.of(SwapProtocolType.REPUTATION, SwapProtocolType.MULTISIG),
                makerNetworkId);
        networkService.addData(offer);
    }

    public void newOpenOffer(SwapOffer offer) {
        OpenOffer openOffer = new OpenOffer(offer);
        openOffers.add(openOffer);
        //  Persistence.write(openOffers);
    }

    public void shutdown() {

    }
}
