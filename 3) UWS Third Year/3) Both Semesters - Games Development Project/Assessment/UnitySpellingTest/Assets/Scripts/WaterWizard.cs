using System.Collections.Generic;
using System.Linq;
using UnityEngine;

public class WaterWizard : MonoBehaviour
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

    //Basic attack particles
    public GameObject attackParticlePrefab;

    //Unique attack mark (before the real attack is launched)
    public GameObject uniqueAttackMarkPrefab;

    //Unique attack particle
    public GameObject uniqueAttackParticlePrefab;

    private bool isAttacking = false;
    private float attackTimer;
    private float attackCd = 1f;
    private int basicAttacksCount = 0;

    private float uniqueAttackTimer;
    private float uniqueAttackCd = 0.75f;
    private int uniqueAttackCount = 0;

    private List<KeyValuePair<GameObject, Vector2>> attackParticlesList;
    private float attackAngle;

    public AudioClip basicAttackAudio;
    public AudioClip rayAttackAudio;

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

        if (attackParticlesList.Count > 0)
        {
            foreach (KeyValuePair<GameObject, Vector2> kvp in attackParticlesList)
                kvp.Key.transform.Translate(kvp.Value * Time.deltaTime * 5f);
        }

        if (!isAttacking && attackTimer > 0)
        {
            if (animator.GetBool("Attack"))
                animator.SetBool("Attack", false);

            if (animator.GetBool("RayAttack"))
                animator.SetBool("RayAttack", false);

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
                else if(uniqueAttackCount <= 2)
                {
                    UniqueAttack();
                    uniqueAttackTimer = uniqueAttackCd;
                }
                else
                {
                    uniqueAttackCount = 0;
                    isAttacking = false;                  
                }

                if (uniqueAttackCount == 2)
                {
                    attackTimer = attackCd;
                    uniqueAttackTimer = uniqueAttackCd;
                    uniqueAttackCount++;                 
                }
            }
        }
    }

    public void BasicAttack()
    {
        animator.SetBool("Attack", true);

        SoundManager.instance.PlaySingle(basicAttackAudio);

        basicAttacksCount++;

        //The basic attack particle direction is defined by [player's position - enemy wizard position]. The particle's speed is defined in the Update function
        Vector2 particleVector = GameObject.FindGameObjectWithTag("Player").transform.position - transform.position;
        particleVector.x /= 3;
        particleVector.y /= 3;

        attackParticlesList.Add(CreateAttackParticle(2f, particleVector));
    }

    public void UniqueAttack()
    {
        animator.SetBool("RayAttack", true);     

        uniqueAttackCount++;

        if (uniqueAttackCount == 1)
        {
            GameObject attackParticle = Instantiate(uniqueAttackMarkPrefab, new Vector2(transform.position.x, transform.position.y - 0.15f), new Quaternion());

            Vector3 playerPosition = GameObject.FindGameObjectWithTag("Player").transform.position;

            float adjacent = Mathf.Abs(transform.position.y - playerPosition.y);
            float opposite = Mathf.Abs(transform.position.x - playerPosition.x);

            attackAngle = Mathf.Atan2(opposite, adjacent) * Mathf.Rad2Deg;

            if (transform.position.x > playerPosition.x)
                attackAngle = -attackAngle;

            attackParticle.transform.Rotate(new Vector3(0, 0, attackAngle));

            Destroy(attackParticle, 0.75f);
        }
        else if (uniqueAttackCount == 2)
        {
            SoundManager.instance.PlaySingle(rayAttackAudio);

            GameObject attackParticle = Instantiate(uniqueAttackParticlePrefab, new Vector2(transform.position.x, transform.position.y - 0.15f), new Quaternion());

            attackParticle.transform.Rotate(new Vector3(0, 0, attackAngle));

            Destroy(attackParticle, 0.75f);
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