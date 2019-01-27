using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F1_Hydrogen : F_Gunman {
    // Child Class F1_Hydrogen inheriting from F_Gunman.








    void Reset()
    {   // Health = 6;
        CostValue = 0;
        ScoreValue = 0;
        MovementSpeed = 1f;
        RunAwayTimer = 3f;
        AttackDamage = 3;
        LookRadius = 3f;
        AttackRadius = 3f;
        AttackRate = 2f;
    }
}