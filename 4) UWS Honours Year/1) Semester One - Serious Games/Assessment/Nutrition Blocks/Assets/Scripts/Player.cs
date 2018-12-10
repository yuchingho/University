using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour {

    ManagerGame ManagerGame;    // Getting the ManagerGame Script.
    Rigidbody2D Rigidbody2D;
    Animator Animator;
    SpriteRenderer SpriteRenderer;
    public GameObject Heart1;
    public GameObject Heart2;
    public GameObject Heart3;

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
        // Add player thinness
        // add ui which shows what was just destroyed. food name, type, calorie value


        if (ManagerGame.PlayerIsPlaying == true) { Movement(); }
        else
        {   // If Player hit by Ball...
            Rigidbody2D.freezeRotation = false; // Stops moving.
            Animator.Play("Idle");              // Freezes animation.
            SpriteRenderer.color = Color.red;   // Changes colour.
            StartCoroutine(Flicker());          // Start respawing.
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
        if (collision.gameObject.name == "Ball")
        {
            ManagerGame.PlayerIsPlaying = false;
            if (ManagerGame.PlayerSpeedCurrent == 0) { ManagerGame.PlayerLives--;  }
        }
    }

    IEnumerator Flicker()
    {
        yield return new WaitForSeconds(0.5f);  // Wait 0.5sec before checking Player Velocity == 0.
        if (ManagerGame.PlayerSpeedCurrent == 0)
        {   // Once Player hits the ground and has stopped moving, start flickering.
            SpriteRenderer.color = Color.Lerp(Color.clear, Color.red, Mathf.PingPong(Time.time, 0.25f));
            yield return new WaitForSeconds(2);
            // After 2 seconds of flickering, respawn and reset values so in starting location.
            transform.position = new Vector3(0, -12.93071f, 0);
            transform.eulerAngles = new Vector3(0, 0, 0);
            transform.localScale = new Vector3(1, 1, 1);
            Rigidbody2D.freezeRotation = true;
            ManagerGame.BallLaunched = false;
            ManagerGame.BallSpeedCurrent = 0;
            ManagerGame.PlayerIsPlaying = true;
            SpriteRenderer.color = Color.white;
            if (ManagerGame.PlayerLives == 2) { Destroy(Heart3); }
            if (ManagerGame.PlayerLives == 1) { Destroy(Heart2); }
            if (ManagerGame.PlayerLives == 0) { Destroy(Heart1); }
        }
    }
}