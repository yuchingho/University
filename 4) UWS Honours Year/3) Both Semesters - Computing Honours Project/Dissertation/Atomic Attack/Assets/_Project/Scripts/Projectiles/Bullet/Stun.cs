using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Stun : _Bullet {

    void Reset()
    {
        Damage = 5;
        Speed = 10;
    }

    protected override void HitTarget()
    {   // So only Units will be Stunned, and not the Castle.
        if (Target.gameObject.name == "E_Gunman(Clone)" || Target.gameObject.name == "E_Swordsman(Clone)")
        { Target.GetComponent<AI_Human>().Stunned = true; }
        base.HitTarget();
    }
}