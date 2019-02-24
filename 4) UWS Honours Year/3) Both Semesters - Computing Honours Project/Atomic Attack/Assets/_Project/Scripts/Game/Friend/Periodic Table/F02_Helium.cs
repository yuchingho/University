using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F02_Helium : MonoBehaviour {

    HealthSystem HealthSystem;
    Rigidbody2D Rigidbody2D;

    public int CostValue;
    public int ScoreValue;
    [SerializeField] protected int MovementSpeed = 1;
    [SerializeField] GameObject Explosion;
    [SerializeField] GameObject SpawnUnits;
    bool Spawn;

    void Start()
    {
        HealthSystem = GetComponent<HealthSystem>();
        Rigidbody2D = GetComponent<Rigidbody2D>();
        Spawn = false;
    }

    void Update()
    {
        transform.Translate(Vector2.right * MovementSpeed * Time.deltaTime);
        if (transform.position.x >= 5.5 || HealthSystem.Deceased == true)
        {
            if (Spawn == false) { SpawnFlying(); }
            if (HealthSystem.Deceased == true)
            {
                Rigidbody2D.gravityScale = 1;
                if (transform.position.y <= -1.45f)
                {
                    // damage building 
                    // and units 
                    // and explosion blowback
                    Destroy(gameObject);
                    Instantiate(Explosion, new Vector2(transform.position.x, transform.position.y), Quaternion.identity);
                }
            }
        }
        if (transform.position.x >= 14) { Destroy(gameObject); }
    }

    void SpawnFlying()
    {
        Instantiate(SpawnUnits, new Vector2(transform.position.x + 0.4f, transform.position.y), Quaternion.identity);
        Instantiate(SpawnUnits, new Vector2(transform.position.x + 0.2f, transform.position.y), Quaternion.identity);
        Instantiate(SpawnUnits, new Vector2(transform.position.x + 0.0f, transform.position.y), Quaternion.identity);
        Instantiate(SpawnUnits, new Vector2(transform.position.x - 0.2f, transform.position.y), Quaternion.identity);
        Instantiate(SpawnUnits, new Vector2(transform.position.x - 0.4f, transform.position.y), Quaternion.identity);
        Spawn = true;
    }
}