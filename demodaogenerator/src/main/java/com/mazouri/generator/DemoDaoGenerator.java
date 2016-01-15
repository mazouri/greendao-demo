package com.mazouri.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DemoDaoGenerator {

    public static void main(String[] args) throws Exception{

        Schema schema = new Schema(1, "mazouri.greendao.gen");

        addNote(schema);

        new DaoGenerator().generateAll(schema, args[0]); // 自动创建
    }

    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");

        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }
}
