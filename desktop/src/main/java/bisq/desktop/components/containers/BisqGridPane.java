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

package bisq.desktop.components.containers;

import bisq.common.data.Pair;
import bisq.desktop.components.controls.BisqLabel;
import bisq.desktop.components.controls.BisqTextArea;
import bisq.desktop.components.controls.BisqTextField;
import bisq.desktop.components.table.BisqTableView;
import bisq.desktop.components.table.TableItem;
import bisq.desktop.layout.Layout;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BisqGridPane extends GridPane {
    public BisqGridPane() {
        setVgap(10);
        setHgap(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(150);
        ColumnConstraints col2 = new ColumnConstraints();
        getColumnConstraints().addAll(col1, col2);
    }

    public BisqLabel startSection(String text) {
        BisqLabel label = new BisqLabel(text);
        label.setLayoutX(4);
        label.setLayoutY(-8);
        label.setPadding(new Insets(0, 7, 0, 5));
        label.getStyleClass().add("titled-group-bg-label-active");
        GridPane.setMargin(label, new Insets(0, 0, 15, 0));
        GridPane.setRowIndex(label, getRowCount());
        GridPane.setColumnIndex(label, 0);
        GridPane.setColumnSpan(label, getColumnCount());
        getChildren().add(label);
        return label;
    }

    public void endSection() {
        addHSpacer();
        // todo add some separator UI element (line)
    }

    public Region addHSpacerMax() {
        Region region = new Region();
        GridPane.setVgrow(region, Priority.ALWAYS);
        GridPane.setRowIndex(region, getRowCount());
        getChildren().add(region);
        return region;
    }

    public Region addHSpacer() {
        return addHSpacer(Layout.SPACING);
    }

    public Region addHSpacer(double height) {
        Region region = new Region();
        region.setMinHeight(height);
        GridPane.setRowIndex(region, getRowCount());
        getChildren().add(region);
        return region;
    }

    public BisqTextField addTextField(String labelText, StringProperty textFieldText) {
        BisqTextField textField = addTextField(labelText, textFieldText.get());
        textField.textProperty().bind(textFieldText);
        return textField;
    }

    public BisqTextField addTextField(String labelText, String textFieldText) {
        BisqTextField textField = new BisqTextField(textFieldText);
        textField.setLabelFloat(true);
        textField.setPromptText(labelText);
        GridPane.setRowIndex(textField, getRowCount());
        GridPane.setColumnIndex(textField, 0);
        GridPane.setColumnSpan(textField, getColumnCount());
        GridPane.setMargin(textField, new Insets(0, 0, 15, 0));
        getChildren().addAll(textField);
        return textField;
    }

    public BisqTextArea addTextArea(String labelText, StringProperty textFieldText) {
        BisqTextArea textArea = addTextArea(labelText);
        textArea.textProperty().bind(textFieldText);
        return textArea;
    }

    public BisqTextArea addTextArea(String labelText) {
        BisqTextArea textArea = new BisqTextArea();
        textArea.setWrapText(true);
        textArea.setPromptText(labelText);
        GridPane.setRowIndex(textArea, getRowCount());
        GridPane.setColumnIndex(textArea, 1);
        GridPane.setMargin(textArea, new Insets(0, 0, 15, 0));
        getChildren().addAll(textArea);
        return textArea;
    }

    public Pair<Button, Label> addButton(String text) {
        Button button = new Button(text);
        Label label = new Label();
        label.setPadding(new Insets(5, 0, 0, 0));

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(button, label);
        GridPane.setRowIndex(hBox, getRowCount());
        GridPane.setColumnIndex(hBox, 0);
        getChildren().add(hBox);
        GridPane.setMargin(hBox, new Insets(0, 0, 15, 0));
        return new Pair<>(button, label);
    }

    public Pair<Button, Label> addButton(String label, Runnable handler) {
        Pair<Button, Label> pair = addButton(label);
        pair.first().setOnAction(e -> handler.run());
        return pair;
    }

    public void addTableView(BisqTableView<? extends TableItem> tableView) {
        GridPane.setRowIndex(tableView, getRowCount());
        GridPane.setColumnIndex(tableView, 0);
        int columnCount = getColumnCount();
        GridPane.setColumnSpan(tableView, columnCount);
        getChildren().add(tableView);
    }


}