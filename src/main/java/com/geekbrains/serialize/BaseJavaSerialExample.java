package com.geekbrains.serialize;

import com.geekbrains.spoonacular.model.RecipesSearchResponseItem;

import java.io.*;

public class BaseJavaSerialExample {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        RecipesSearchResponseItem item;
        /*= RecipesSearchResponseItem.builder()
                .id(654959L)
                .title("Pasta With Tuna")
                .image("https://spoonacular.com/recipeImages/654959-312x231.jpg")
                .imageType("jpg")
                .build();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serial_file.txt"));
        oos.writeObject(item);
        oos.close();*/
        // write to the file the binary object "Serialization"//

        // got from the file after Serialization by Deserialization - works both only by Java JDK same version!! //
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serial_file.txt"));
        item = (RecipesSearchResponseItem) ois.readObject();
        System.out.println(item);
    }
}
