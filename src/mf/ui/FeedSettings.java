package mf.ui;

import arc.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.logic.*;

public class FeedSettings{

    public static void load(){
        Vars.ui.settings.addCategory("MindFeed", Icon.chat, e -> {
            e.sliderPref("kfeed-direction-int", 1, 1, 2, 1, st -> {
                Core.settings.put("kfeed-direction", st == 1);
                return st == 1 ? "Left" : "Right";
            });
        });
    }
}
