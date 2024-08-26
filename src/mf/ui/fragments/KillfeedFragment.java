package mf.ui.fragments;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.scene.*;
import arc.scene.actions.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ui.*;

public class KillfeedFragment{

    public Table table;
    public Table prevLog;

    public void build(Group parent){
        parent.fill(e -> {
            table = e;
            e.name = "kfeed";
            e.update(() -> e.align(Core.settings.getBool("kfeed-direction", true) ? Align.topLeft : Align.topRight));
            e.defaults().align(Core.settings.getBool("kfeed-direction") ? Align.left : Align.right);
            e.y = -90;
        });
    }

    public static TextureRegion uimage(UnitType unit){
        return Core.atlas.find(
                Core.atlas.has(unit.name) ? "unit-" + unit.name + "-ui" : unit.name,
                Core.atlas.has("unit-" + unit.name + "-ui") ? "unit-" + unit.name + "-ui" : unit.name
        );
    }

    public void trackDeath(@Nullable Unitc u1, @Nullable Unitc u2){
        if(u1 == null && u2 == null) return; //do nothing if both are null
        Cell<Table> t = table.table(Styles.black5, log -> {
            log.defaults().pad(5);
            log.align(Core.settings.getBool("kfeed-direction", true) ? Align.left : Align.right);
            if(u1 != null && u2 != null){
                log.image(uimage(u1.type())).size(45).scaling(Scaling.fit);
                log.add("<" + u1.team().coloredName() + "> was killed by <" + u2.team().coloredName() + ">");
                log.image(uimage(u2.type())).size(45).scaling(Scaling.fit);
            }else if(u1 != null){
                log.image(uimage(u1.type())).size(45).scaling(Scaling.fit);
                log.add("<" + u1.team().coloredName() + "> died");
            }else{
                log.image(uimage(u2.type())).size(45).scaling(Scaling.fit);
                log.add("<" + u2.team().coloredName() + "> died");
            }
        }).height(50);
        t.row();


        t.get().actions(
                Actions.delay(2),
                Actions.fadeOut(2),
                Actions.run(() -> {
                    table.getCells().remove(t);
                }),
                Actions.remove()
        );
    }
}
