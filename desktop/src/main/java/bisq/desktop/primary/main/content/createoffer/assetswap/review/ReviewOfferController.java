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

package bisq.desktop.primary.main.content.createoffer.assetswap.review;

import bisq.application.DefaultServiceProvider;
import bisq.desktop.common.view.Controller;
import bisq.offer.SwapOffer;
import bisq.offer.OfferService;
import bisq.offer.OpenOfferService;
import lombok.Getter;

public class ReviewOfferController implements Controller {
    private final ReviewOfferModel model;
    @Getter
    private final ReviewOfferView view;
    private final OfferService offerService;
    private final OpenOfferService openOfferService;

    public ReviewOfferController(DefaultServiceProvider serviceProvider) {
        model = new ReviewOfferModel();
        view = new ReviewOfferView(model, this);

        offerService = serviceProvider.getOfferService();
        openOfferService = serviceProvider.getOpenOfferService();
    }

    public void setAskValue(String value) {
        model.setAskValue(value);
    }

    public void onPublish() {
        SwapOffer offer = offerService.createOffer(model.askAmount);
        offerService.publishOffer(offer);
        openOfferService.newOpenOffer(offer);
    }
}
