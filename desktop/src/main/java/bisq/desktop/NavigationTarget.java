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

package bisq.desktop;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public enum NavigationTarget {
    NONE(),
    ROOT(),

    OVERLAY(ROOT),
    POPUP_WINDOW(OVERLAY),
    OFFER_DETAILS(OVERLAY),
    CREATE_OFFER(OVERLAY),

    PRIMARY_STAGE(ROOT),

    MAIN(PRIMARY_STAGE),
    CONTENT(MAIN),
    
    SETTINGS(CONTENT),
    PREFERENCES(SETTINGS),
    
    NETWORK_INFO(SETTINGS),
    CLEAR_NET(NETWORK_INFO),
    TOR(NETWORK_INFO),
    I2P(NETWORK_INFO),

    ABOUT(SETTINGS),

    SOCIAL(CONTENT),
    TRADE_INTENT(SOCIAL),
    HANGOUT(SOCIAL),

    MARKETS(CONTENT),
    OFFERBOOK(CONTENT),

    PORTFOLIO(CONTENT),
    WALLET(CONTENT);


    @Getter
    private final Optional<NavigationTarget> parent;
    @Getter
    private final List<NavigationTarget> path;

    NavigationTarget() {
        parent = Optional.empty();
        path = new ArrayList<>();
    }

    NavigationTarget(NavigationTarget parent) {
        this.parent = Optional.of(parent);
        List<NavigationTarget> temp = new ArrayList<>();
        Optional<NavigationTarget> candidate = Optional.of(parent);
        while (candidate.isPresent()) {
            temp.add(0, candidate.get());
            candidate = candidate.get().getParent();
        }
        this.path = temp;
    }
}