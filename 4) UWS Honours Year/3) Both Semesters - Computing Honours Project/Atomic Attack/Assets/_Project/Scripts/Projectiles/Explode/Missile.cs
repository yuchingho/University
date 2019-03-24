using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Missile : _Explode {

    Rigidbody2D Rigidbody2D;

    [SerializeField] protected float MovementSpeed;
    [SerializeField] protected GameObject Mist;





    protected virtual void Start()
    {
        Rigidbody2D = GetComponent<Rigidbody2D>();
        MovementSpeed = Random.Range(3.7f, 5.0f);
    }

    protected virtual void Update()
    {
        transform.Translate(Vector2.up * MovementSpeed * Time.deltaTime);
        if (transform.position.x >= 3)
        {
            Rigidbody2D.gravityScale = 0.4f;
            gameObject.layer = 13;
        }
    }
}