// Navbar com sombra ao rolar
const navbar = document.getElementById('navbar');
window.addEventListener('scroll', () => {
  navbar.classList.toggle('scrolled', window.scrollY > 40);
});

// Menu mobile
const navToggle = document.getElementById('navToggle');
const navLinks = document.getElementById('navLinks');
navToggle.addEventListener('click', () => navLinks.classList.toggle('open'));
navLinks.querySelectorAll('a').forEach(a =>
  a.addEventListener('click', () => navLinks.classList.remove('open'))
);

// Animação de entrada das seções
const observer = new IntersectionObserver(
  entries => entries.forEach(e => e.isIntersecting && e.target.classList.add('visible')),
  { threshold: 0.12 }
);
document
  .querySelectorAll('.section .container, .highlights-grid, .cta-final .container')
  .forEach(el => {
    el.classList.add('reveal');
    observer.observe(el);
  });

// Formulário de agendamento → WhatsApp com mensagem pronta (só existe na página inicial)
const WHATSAPP = '5513996081486';
document.getElementById('bookingForm')?.addEventListener('submit', e => {
  e.preventDefault();
  const nome = document.getElementById('bkName').value.trim();
  const servico = document.getElementById('bkService').value;
  const msg = document.getElementById('bkMsg').value.trim();

  let texto = 'Olá, Patrick! ';
  if (nome) texto += `Me chamo ${nome}. `;
  texto += `Quero agendar: ${servico}.`;
  if (msg) texto += ` ${msg}`;

  window.open(`https://wa.me/${WHATSAPP}?text=${encodeURIComponent(texto)}`, '_blank');
});

// Ano do rodapé
document.getElementById('year').textContent = new Date().getFullYear();
