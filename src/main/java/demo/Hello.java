package demo;

import com.github.freewind.lostlist.Lists;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Hello extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static List<String> columnNames = Arrays.asList("ID", "Name");
    private static List<String> data = Lists.newList("123", "JavaFX");

    @Override
    public void start(Stage primaryStage) throws NoSuchMethodException {
        primaryStage.setTitle("Hello");
        VBox root = new VBox() {{
            getChildren().add(createForm());
        }};
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private VBox createForm() throws NoSuchMethodException {
        return new VBox() {{
            for (int i = 0; i < columnNames.size(); i++) {
                final int index = i;
                final String columnName = columnNames.get(i);
                getChildren().addAll(
                        new HBox() {{
                            getChildren().addAll(
                                    new Label(columnName) {{
                                        setPrefWidth(50);
                                    }},
                                    new TextField() {{
                                        textProperty().bindBidirectional(createColumnProperty(index));
                                    }}
                            );
                            setSpacing(10);
                            setPadding(new Insets(5));
                        }}
                );
            }
            getChildren().add(
                    new Button("Print current data in console") {{
                        setOnAction(event -> {
                            System.out.println(StringUtils.join(data));
                        });
                    }}
            );
        }};
    }

    private static Property<String> createColumnProperty(int index) throws NoSuchMethodException {
        return JavaBeanStringPropertyBuilder.create()
                .bean(new DataBean(data, index))
                .name("value")
                .build();
    }


}

