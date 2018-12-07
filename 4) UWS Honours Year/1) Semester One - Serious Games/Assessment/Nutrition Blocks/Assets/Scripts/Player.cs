using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour {

    ManagerGame ManagerGame;    // Getting the ManagerGame Script.
    Rigidbody2D Rigidbody2D;
    Animator Animator;
    SpriteRenderer SpriteRenderer;

    void Start()
    {
        ManagerGame = GameObject.Find("ManagerGame").GetComponent<ManagerGame>();
        Rigidbody2D = GetComponent<Rigidbody2D>();
        Animator = GetComponent<Animator>();
        SpriteRenderer = GetComponent<SpriteRenderer>();
    }

    void Update()
    {

        ManagerGame.PlayerSpeedCurrent = Rigidbody2D.velocity.magnitude;
        if (ManagerGame.PlayerIsPlaying == true) { Movement(); }
        else
        {   // If Player hit by Ball...
            Rigidbody2D.freezeRotation = false; // Stops moving.
            Animator.Play("Idle");              // Freezes animation.
            SpriteRenderer.color = Color.red;   // Changes colour.
            StartCoroutine(Flicker());
            // Lives --
        }
    }

    void Movement()
    {
        if (Input.GetKey(KeyCode.LeftArrow))
        {   // LocalScale: x == left/right | y == up/down.
            Animator.Play("Running");
            transform.localScale = new Vector2(-1, 1);
            transform.position += Vector3.left * Time.deltaTime * ManagerGame.PlayerSpeed;
        }
        else if (Input.GetKey(KeyCode.RightArrow))
        {
            Animator.Play("Running");
            transform.localScale = new Vector2(1, 1);
            transform.position += Vector3.right * Time.deltaTime * ManagerGame.PlayerSpeed;
        }
        else { Animator.Play("Idle"); }
    }

    void OnTriggerEnter2D(Collider2D collision)
    {   // If Player hit by Ball, Player stops moving.
        if (collision.gameObject.name == "Ball") { ManagerGame.PlayerIsPlaying = false; }
    }

    IEnumerator Flicker()
    {   // Flickers before disappearing, then respawning with reset values.
        if (ManagerGame.PlayerSpeedCurrent == 0)
        {
            SpriteRenderer.color = Color.Lerp(Color.clear, Color.red, Mathf.PingPong(Time.time, 0.2f));
        }
        yield return new WaitForSeconds(1);
        transform.position = new Vector2(0, -12.93071f);
        transform.eulerAngles = new Vector3(0, 0, 0);
        transform.localScale = new Vector2(1, 1);
        Rigidbody2D.freezeRotation = true;
        ManagerGame.BallLaunched = false;
        //ManagerGame.BallSpeedCurrent = 0;
        ManagerGame.PlayerIsPlaying = true;
        SpriteRenderer.color = Color.white;
        // need to reassign bouncy material on ball in ball script
        // https://answers.unity.com/questions/14242/how-to-change-physics-material-of-a-colider-in-run.html
    }
}