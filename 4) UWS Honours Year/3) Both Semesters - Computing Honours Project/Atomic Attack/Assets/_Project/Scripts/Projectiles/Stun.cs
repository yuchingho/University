using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Stun : _Bullet {

    [SerializeField] GameObject Effect;

    // need to differentiate between castle and lil guy.
    // cuz castle do normal explosion, while lil guy do stun effect

    protected override void HitTarget()
    {
        base.HitTarget();
    }
}