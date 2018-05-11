using System.Collections.Generic;
using UnityEngine;

public class OutcastWizard : MonoBehaviour
{
    private float speed = 5f;
    private float movementTimer;
    private float movementCd = 1f;

    [SerializeField] private float leftEdge;
    [SerializeField] private float rightEdge;
    [SerializeField] private float bottomEdge;
    [SerializeField] private float topEdge;

    private Animator animator;

    private Vector2 movementVector;

    public GameObject attackParticlePrefab;

    private List<KeyValuePair<GameObject, Vector2>> attackParticlesList;

    void Start()
    {
        movementTimer = movementCd;
        movementVector = DefineMovementVector();

        animator = gameObject.GetComponent<Animator>();
    }

    void Update()
    {
        Movements();
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