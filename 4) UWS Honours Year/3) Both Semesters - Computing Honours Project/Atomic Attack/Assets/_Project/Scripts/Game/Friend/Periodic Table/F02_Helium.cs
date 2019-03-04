using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F02_Helium : MonoBehaviour {

    HealthSystem HealthSystem;
    Rigidbody2D Rigidbody2D;
    CapsuleCollider2D CapsuleCollider2D;
    public int CostValue;
    public int ScoreValue;
    [SerializeField] protected int MovementSpeed = 1;
    [SerializeField] GameObject SpawnUnits;
    bool Spawn;


    void Start()
    {
        HealthSystem = GetComponent<HealthSystem>();
        Rigidbody2D = GetComponent<Rigidbody2D>();
        CapsuleCollider2D = GetComponent<CapsuleCollider2D>();
        Spawn = false;
    }

    void Update()
    {
        transform.Translate(Vector2.right * MovementSpeed * Time.deltaTime);
        if (transform.position.x >= 5.5 || HealthSystem.Deceased == true)
        {   // Checking if F_Swordsmen have spawned yet, and only spawning certain number.
            if (Spawn == false) { SpawnFlying(); }
            if (HealthSystem.Deceased == true)  // If shot down, will fall to Ground.
            { Rigidbody2D.gravityScale = 1; CapsuleCollider2D.enabled = true; }
        }   // Out of Bounds.
        if (transform.position.x >= 14) { Destroy(gameObject); }
    }

    void SpawnFlying()
    {
        Instantiate(SpawnUnits, new Vector2(transform.position.x + 0.2f, transform.position.y), Quaternion.identity);
        Instantiate(SpawnUnits, new Vector2(transform.position.x       , transform.position.y), Quaternion.identity);
        Instantiate(SpawnUnits, new Vector2(transform.position.x - 0.2f, transform.position.y), Quaternion.identity);
        Spawn = true;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground") { StartCoroutine(DelayDisappear()); }
    }

    IEnumerator DelayDisappear()
    {
        MovementSpeed = 0;
        // add crush units damage, damage all
        // add new GameObject, layer set to default, onTriggerEnter, damage all?
        yield return new WaitForSeconds(3f);
        Destroy(gameObject);
    }
}