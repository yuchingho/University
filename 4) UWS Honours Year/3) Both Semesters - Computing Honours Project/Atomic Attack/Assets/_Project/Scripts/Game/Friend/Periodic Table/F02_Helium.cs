using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F02_Helium : MonoBehaviour {

    HealthSystem HealthSystem;
    Rigidbody2D Rigidbody2D;

    public int CostValue;
    public int ScoreValue;
    [SerializeField] protected float MovementSpeed = 1f;
    [SerializeField] GameObject Explosion;
    [SerializeField] GameObject SpawnUnits;

    void Start()
    {
        HealthSystem = GetComponent<HealthSystem>();
        Rigidbody2D = GetComponent<Rigidbody2D>();
    }

    void Update()
    {
        transform.Translate(Vector2.right * MovementSpeed * Time.deltaTime);
        if (HealthSystem.Deceased == true || transform.position.x >= 5.75)
        {
            Rigidbody2D.gravityScale = 1;
            if (transform.position.y <= -1.45f)
            {
                Destroy(gameObject);
                Instantiate(Explosion, new Vector2(transform.position.x, transform.position.y), Quaternion.identity);
            }
            // damage building 
            // and units 
            // and explosion blowback
            if (HealthSystem.Deceased == true)
            {   // Only if E_Gunmen kill the Blimp, add Score.
                // score
            }
            SpawnFlying();
        }
    }

    void SpawnFlying()
    {
        //Instantiate(ShockUnits, new Vector2(transform.position.x, transform.position.y), Quaternion.identity);
        // Spawn 5, and when drops down, can see the 5 in a row.
    }

    // instiate blimp at spawn location + y.
    // obj will float along top of screen, will have HP
    // once destroyed, spawn swordsman which will drop down and start running along ground
}