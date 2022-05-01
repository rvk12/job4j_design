package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Info info = new Info(added, changed, deleted);
        Map<Integer, String> prevMap = new HashMap();
        for (User p : previous) {
            prevMap.put(p.getId(), p.getName());
        }
        for (User cu : current) {
            String val = prevMap.get(cu.getId());
            if (val != null) {
                if (!val.equals(cu.getName())) {
                    info.setChanged(++changed);
                }
            } else {
                info.setAdded(++added);
            }
            prevMap.remove(cu.getId());
        }
        int removed = prevMap.size();
        info.setDeleted(removed);
        return info;
    }
}