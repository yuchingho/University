using System.Collections.Generic;
using System.Linq;
using UnityEngine;

public class FireWizard : MonoBehaviour
{
    private float speed = 5f;
    private float movementTimer;
    private float movementCd = 1f;

    public float leftEdge;
    public float rightEdge;
    public float bottomEdge;
    public float topEdge;

    private Animator animator;

    private Vector2 movementVector;

    public GameObject attackParticlePrefab;

    private bool isAttacking = false;
    private float attackTimer;
    private float attackCd = 1f;
    private int basicAttacksCount = 0;

    private float uniqueAttackTimer;
    private float uniqueAttackCd = 0.3f;
    private int uniqueAttackCount = 0;

    private List<KeyValuePair<GameObject, Vector2>> attackParticlesList;

    public AudioClip basicAttackAudio;

    void Start()
    {
        movementTimer = movementCd;
        movementVector = DefineMovementVector();

        attackTimer = attackCd;

        attackParticlesList = new List<KeyValuePair<GameObject, Vector2>>();

        animator = gameObject.GetComponent<Animator>();

        GameObject.Find("SceneHandler").GetComponent<SceneHandlerScript>().SetSceneToPlay(UnityEngine.SceneManagement.SceneManager.GetActiveScene().buildIndex);
    }

    void Update()
    {
        //Deletes every destroyed particle from the list
        attackParticlesList = attackParticlesList.Where(kvp => kvp.Key != null).ToList();

        foreach (KeyValuePair<GameObject, Vector2> kvp in attackParticlesList)
            kvp.Key.transform.Translate(kvp.Value * Time.deltaTime * 5f);

        if (!isAttacking && attackTimer > 0)
        {
            if (animator.GetBool("Attack"))
                animator.SetBool("Attack", false);

            Movements();
            attackTimer -= Time.deltaTime;
        }
        else
        {
            if (!isAttacking && basicAttacksCount < 3)
            {
                BasicAttack();
                attackTimer = attackCd;
            }
            else
            {
                basicAttacksCount = 0;

                if (!isAttacking)
                    isAttacking = true;

                if (uniqueAttackTimer > 0)
                    uniqueAttackTimer -= Time.deltaTime;
                else
                {
                    UniqueAttack();
                    uniqueAttackTimer = uniqueAttackCd;
                }

                if (uniqueAttackCount == 3)
                {
                    uniqueAttackCount = 0;
                    attackTimer = attackCd;
                    isAttacking = false;
                }
            }
        }
    }

    public void BasicAttack()
    {
        animator.SetBool("Attack", true);

        SoundManager.instance.PlaySingle(basicAttackAudio);

        basicAttacksCount++;

        //The basic attack particle direction is defined by [player's position - firewizard position]. The particle's speed is defined in the Update function
        Vector2 particleVector = GameObject.FindGameObjectWithTag("Player").transform.position - transform.position;
        particleVector.x /= 3;
        particleVector.y /= 3;

        attackParticlesList.Add(CreateAttackParticle(2f, particleVector));
    }

    public void UniqueAttack()
    {
        animator.SetBool("Attack", true);

        SoundManager.instance.PlaySingle(basicAttackAudio);

        uniqueAttackCount++;  

        for(float f = -0.6f; f <= 0.6f; f += 0.2f)
        {
            Vector2 particleVector = new Vector2(f, -0.5f);

            attackParticlesList.Add(CreateAttackParticle(4f, particleVector));
        }     
    }

    public KeyValuePair<GameObject, Vector2> CreateAttackParticle(float lifeSpan, Vector2 particleVector)
    {
        GameObject attackParticle = Instantiate(attackParticlePrefab, new Vector2(transform.position.x, transform.position.y), new Quaternion());
        Destroy(attackParticle, lifeSpan);

        return new KeyValuePair<GameObject, Vector2>(attackParticle, particleVector);
    }

    public void Movements()
    {
        if (movementTimer > 0)
        {
            animator.SetFloat("HorizontalSpeed", movementVector.x);
            animator.SetFloat("VerticalSpeed", movementVector.y);

            movementTimer -= Time.deltaTime;
            transform.Translate(movementVector * Time.deltaTime * speed);

            //Blocks movement at the edges of the screen
            var pos = Camera.main.WorldToViewportPoint(transform.position);
            pos.x = Mathf.Clamp(pos.x, leftEdge, rightEdge);
            pos.y = Mathf.Clamp(pos.y, bottomEdge, topEdge);
            transform.position = Camera.main.ViewportToWorldPoint(pos);
        }
        else
        {
            movementTimer = movementCd;
            movementVector = DefineMovementVector();
        }
    }

    public Vector2 DefineMovementVector()
    {
        return new Vector2(Random.Range(-0.5f, 0.5f), Random.Range(-0.3f, 0.3f));
    }
}