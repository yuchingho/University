using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Gunman : Human {

    [Space(10), Header("[^ Child: Gunman ]")]
    [SerializeField] float Delete;

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground")
        {
            Destroy(GetComponent<SpawnOnCastle>());
        }
    }
}