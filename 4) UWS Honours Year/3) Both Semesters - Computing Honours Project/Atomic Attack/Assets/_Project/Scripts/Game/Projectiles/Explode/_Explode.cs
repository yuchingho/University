using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class _Explode : MonoBehaviour {

    public bool Magnify;
    public int Damage;

    public void MagnifyExplosions()
    {
        // if in magnified area, will cause lil guys to fly away.
        // add screen-shake related to damage
        // don't need to calculate FallDamage cause damage should already kill them
    }
}