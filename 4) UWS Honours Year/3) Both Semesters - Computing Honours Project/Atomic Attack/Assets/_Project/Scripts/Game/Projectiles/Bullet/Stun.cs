using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Stun : _Bullet {

    void Reset()
    {
        Damage = 2;
        Speed = 5;
    }

    protected override void HitTarget()
    {
        if (Target.gameObject.name == "E_Swordsman(Clone)" || Target.gameObject.name == "E_Gunman(Clone)")
        { Target.GetComponent<AI_Human>().Stunned = true; }
        base.HitTarget();
    }
}