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

package bisq.desktop.primary.main.content.markets;

import bisq.application.DefaultServiceProvider;
import bisq.desktop.common.threading.UIThread;
import bisq.desktop.common.view.Controller;
import lombok.Getter;

public class MarketsController implements Controller {
    public static final Class<? extends Controller> controllerClass  =MarketsController.class;

    private final DefaultServiceProvider serviceProvider;
    private final MarketsModel model;
    @Getter
    private final MarketsView view;

    public MarketsController(DefaultServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        model = new MarketsModel();
        view = new MarketsView(model, this);
    }

   
    void onRefresh() {
        serviceProvider.getMarketPriceService().request()
                .whenComplete((marketPriceMap, t) -> UIThread.run(() -> model.setMarketPriceMap(marketPriceMap)));
    }
}
