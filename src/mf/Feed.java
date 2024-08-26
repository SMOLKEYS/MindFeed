package mf;

import arc.*;
import mf.ui.*;
import mf.ui.fragments.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.mod.*;

public class Feed extends Mod{

    public KillfeedFragment kfrag;

    public Feed(){
        Events.run(EventType.ClientLoadEvent.class, () -> {
            FeedSettings.load();

            kfrag = new KillfeedFragment();

            kfrag.build(Vars.ui.hudGroup);

            Events.on(EventType.UnitBulletDestroyEvent.class, lis -> {
                kfrag.trackDeath(lis.unit, lis.bullet.owner instanceof Unitc uc ? uc : null);
            });
        });
    }
}
