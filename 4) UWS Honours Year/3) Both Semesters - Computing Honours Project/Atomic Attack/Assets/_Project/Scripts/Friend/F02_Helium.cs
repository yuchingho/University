using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F02_Helium : F_Swordsman {

    // Child Class F02_Helium inheriting from F_Swordsman.
    [Space(10), Header("[^ Child: F02_Helium ]")]
    #pragma warning disable
    [SerializeField] string Effect = "Spawn from Blimp";

    // instiate object at spawn location + y.
    // obj will float along top of screen, will have HP
    // once destroyed, spawn swordsman which will drop down and start running along ground
}