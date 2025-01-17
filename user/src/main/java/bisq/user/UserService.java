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

package bisq.user;

import bisq.persistence.Persistence;
import bisq.persistence.PersistenceClient;
import bisq.persistence.PersistenceService;
import lombok.Getter;

public class UserService implements PersistenceClient<UserModel> {
    @Getter
    private final Persistence<UserModel> persistence;
    @Getter
    private final UserModel userModel = new UserModel();

    public UserService(PersistenceService persistenceService) {
        persistence = persistenceService.getOrCreatePersistence(this, "db", userModel);
    }

    @Override
    public void applyPersisted(UserModel persisted) {
        userModel.applyPersisted(persisted);
    }

    @Override
    public UserModel getClone() {
        return userModel.getClone();
    }
}