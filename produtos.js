// Carrinho simples: monta o pedido e envia pelo WhatsApp
const ZAP = '5513996081486';
const fmt = v => 'R$ ' + v.toFixed(2).replace('.', ',');

const cards = [...document.querySelectorAll('.product-card')];
const cartBar = document.getElementById('cartBar');
const cartCount = document.getElementById('cartCount');
const cartTotal = document.getElementById('cartTotal');

function updateCart() {
  let itens = 0;
  let total = 0;
  cards.forEach(card => {
    const qty = Number(card.dataset.qty || 0);
    if (qty > 0) {
      itens += qty;
      total += qty * Number(card.dataset.price);
    }
  });
  cartBar.hidden = itens === 0;
  document.body.classList.toggle('has-cart', itens > 0);
  cartCount.textContent = itens === 1 ? '1 item' : `${itens} itens`;
  cartTotal.textContent = fmt(total);
}

cards.forEach(card => {
  const btnAdd = card.querySelector('.btn-add');
  const stepper = card.querySelector('.qty-stepper');
  const qtyValue = card.querySelector('.qty-value');

  const setQty = qty => {
    card.dataset.qty = qty;
    qtyValue.textContent = qty;
    const active = qty > 0;
    btnAdd.hidden = active;
    stepper.hidden = !active;
    card.classList.toggle('in-cart', active);
    updateCart();
  };

  btnAdd.addEventListener('click', () => setQty(1));
  card.querySelector('.qty-plus').addEventListener('click', () => setQty(Number(card.dataset.qty) + 1));
  card.querySelector('.qty-minus').addEventListener('click', () => setQty(Number(card.dataset.qty) - 1));
});

document.getElementById('cartSend').addEventListener('click', () => {
  const linhas = [];
  let total = 0;
  cards.forEach(card => {
    const qty = Number(card.dataset.qty || 0);
    if (qty > 0) {
      const sub = qty * Number(card.dataset.price);
      total += sub;
      linhas.push(`• ${card.dataset.name} (${qty}x) — ${fmt(sub)}`);
    }
  });
  const texto = `Olá, Patrick! Quero comprar estes produtos do PK Beauty:\n${linhas.join('\n')}\nTotal: ${fmt(total)}\n\nComo combinamos o pagamento e a retirada/entrega?`;
  window.open(`https://wa.me/${ZAP}?text=${encodeURIComponent(texto)}`, '_blank');
});
