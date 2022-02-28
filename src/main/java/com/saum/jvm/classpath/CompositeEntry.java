package com.saum.jvm.classpath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author saum
 * @Description: CompositeEntry由众多的Entry组成，如：aaa1/bbb/ccc;aaa2/bbb/ccc;aaa3/bbb/ccc;
 */
public class CompositeEntry extends Entry {

    private final List<Entry> entryList;

    public CompositeEntry(String pathList){
        String[] paths = pathList.split(File.pathSeparator);
        entryList = new ArrayList<>(paths.length);
        for(String path : paths){
            entryList.add(Entry.createEntry(path));
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        for(Entry entry : entryList){
            try {
                return entry.readClass(className);
            } catch (IOException e) {
            }
        }
        throw new IOException("class not found " + className);
    }

    @Override
    public String printClassName() {
        String[] strs = new String[entryList.size()];
        for(int i = 0; i < entryList.size(); i++){
            strs[i] = entryList.get(i).printClassName();
        }
        return String.join(File.pathSeparator, strs);
    }
}
