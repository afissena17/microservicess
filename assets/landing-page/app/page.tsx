const services = [
  { category: "Service Perpustakaan", items: [
    { url: "https://perpustakaan-gateway.mooo.com", name: "Perpustakaan API Gateway", desc: "Gateway utama sistem perpustakaan", img: "https://i.pinimg.com/736x/58/b9/51/58b951b47a8262d6931cf81c9c639136.jpg" },
    { url: "https://anggota.mooo.com", name: "Anggota", desc: "Manajemen data anggota", img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkkq1-UatZx9MOV0RVPzst5L5lAfhHcOb4vg&s" },
    { url: "https://bukuu.mooo.com", name: "Buku", desc: "Katalog dan stok buku", img: "https://i.pinimg.com/736x/b7/a2/91/b7a29170bb6239aec83517ff690c0273.jpg" },
    { url: "https://peminjamann.mooo.com", name: "Peminjaman", desc: "Transaksi peminjaman buku", img: "https://i.pinimg.com/736x/c8/95/df/c895dfdd89012367161154256c4973ef.jpg" },
    { url: "https://pengembalian.mooo.com", name: "Pengembalian", desc: "Transaksi pengembalian & denda", img: "https://i.pinimg.com/1200x/0e/2f/08/0e2f08f7f1e312c0b52f7d5e02266463.jpg" },
  ]},
  { category: "Service Marketplace", items: [
    { url: "https://marketplace-gateway.mooo.com", name: "Marketplace API Gateway", desc: "Gateway utama sistem jual-beli", img: "https://ap-southeast-2-seek-apac.graphassets.com/AEzBCRO50TYyqbV6XzRDQz/JKw7oEAYQcassUca0Z0b" },
    { url: "https://pelanggan.mooo.com", name: "Pelanggan", desc: "Akun pengguna (penjual/pembeli)", img: "https://plus.unsplash.com/premium_vector-1682303382733-f64ce64cace0?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" },
    { url: "https://produk.mooo.com", name: "Produk", desc: "Manajemen data produk jual", img: "https://i.pinimg.com/1200x/66/e7/a3/66e7a3964e7e7d7d4f7016c454d157ef.jpg" },
    { url: "https://orderr.mooo.com", name: "Order", desc: "Keranjang belanja dan pesanan", img: "https://i.pinimg.com/736x/71/21/3c/71213c4cf9f67a108a2f9e4dd0e10b62.jpg" },
  ]},
  { category: "Service Infrastructure", items: [
    { url: "https://jenkinss.mooo.com", name: "Jenkins", desc: "CI/CD otomatisasi deployment", img: "https://image.web.id/images/logo-title-opengraph.png" },
    { url: "https://graffana.mooo.com", name: "Grafana", desc: "Dashboard monitoring metrics", img: "https://pandorafms.com/blog/wp-content/uploads/2019/02/que-es-grafana-1.png" },
    { url: "https://kibbana.mooo.com", name: "Kibana", desc: "Dashboard logs & searching", img: "https://icon-icons.com/download-file?file=https%3A%2F%2Fimages.icon-icons.com%2F2699%2FPNG%2F512%2Felasticco_kibana_logo_icon_169209.png&id=169209&pack_or_individual=pack" },
    { url: "https://eurekaa.mooo.com", name: "Eureka", desc: "Service Discovery & Registry", img: "https://velog.velcdn.com/images/rockstar/post/db8e6c33-a1cf-4e2d-92a6-adf7aba0ccb3/image.png" },
    { url: "https://rabbittmq.mooo.com", name: "RabbitMQ", desc: "Message Broker Management", img: "https://shiftasia.com/community/content/images/2024/04/rabbitmq_logo_icon_170812.png" },
  ]},
  { category: "Service Database", items: [
    { url: "https://dbh2.mooo.com", name: "H2 Console", desc: "Console H2 Database (Relational)", img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQI9Cge7YxJfqfABoys4JVhlRctcUswBDVm2A&s" },
    { url: "https://dbmongo.mooo.com", name: "MongoDB", desc: "Console Admin MongoDB (NoSQL)", img: "https://i.pinimg.com/1200x/33/1d/cc/331dcc50a90f10d6da211faf28678b0f.jpg" },
  ]},
];

const dockerImages = [
  { category: "Images Perpustakaan", items: [
    { url: "https://hub.docker.com/r/itsanla/perpustakaan-gateway", name: "Perpustakaan Gateway" },
    { url: "https://hub.docker.com/r/itsanla/anggota", name: "Anggota" },
    { url: "https://hub.docker.com/r/itsanla/buku", name: "Buku" },
    { url: "https://hub.docker.com/r/itsanla/peminjaman", name: "Peminjaman" },
    { url: "https://hub.docker.com/r/itsanla/pengembalian", name: "Pengembalian" },
  ]},
  { category: "Images Marketplace", items: [
    { url: "https://hub.docker.com/r/itsanla/marketplace-gateway", name: "Marketplace Gateway" },
    { url: "https://hub.docker.com/r/itsanla/pelanggan", name: "Pelanggan" },
    { url: "https://hub.docker.com/r/itsanla/produk", name: "Produk" },
    { url: "https://hub.docker.com/r/itsanla/order", name: "Order" },
  ]},
  { category: "Images Infrastructure", items: [
    { url: "https://hub.docker.com/r/itsanla/eureka", name: "Eureka" },
    { url: "https://hub.docker.com/r/itsanla/cqrs", name: "CQRS" },
    { url: "https://hub.docker.com/_/rabbitmq", name: "RabbitMQ" },
    { url: "https://hub.docker.com/_/mongo", name: "MongoDB" },
    { url: "https://hub.docker.com/_/mongo-express", name: "Mongo Express" },
    { url: "https://hub.docker.com/r/jenkins/jenkins", name: "Jenkins" },
    { url: "https://hub.docker.com/r/elastic/elasticsearch", name: "Elasticsearch" },
    { url: "https://hub.docker.com/r/elastic/logstash", name: "Logstash" },
    { url: "https://hub.docker.com/r/elastic/kibana", name: "Kibana" },
    { url: "https://hub.docker.com/r/grafana/grafana", name: "Grafana" },
    { url: "https://hub.docker.com/r/prom/prometheus", name: "Prometheus" },
  ]},
];

export default function Home() {
  return (
    <div className="min-h-screen bg-white">
      <nav className="bg-white shadow-md sticky top-0 z-50">
        <div className="container mx-auto px-6 max-w-7xl">
          <div className="flex justify-between items-center h-20">
            <div className="flex items-center gap-2">
              <div className="w-10 h-10 rounded-lg flex items-center justify-center overflow-hidden">
                <img src="https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img,w_235,h_235/https://www.pnp.ac.id/wp-content/uploads/2025/01/LOGO-PNP.png" alt="Logo" className="w-full h-full object-cover" />
              </div>
              <span className="text-xl font-bold text-gray-900">Microservices</span>
            </div>
            
            <div className="flex items-center gap-1">
              {dockerImages.map((section) => (
                <div key={section.category} className="relative group">
                  <button className="px-4 py-2 text-gray-700 hover:text-orange-600 hover:bg-orange-50 rounded-lg font-medium transition-colors">
                    {section.category}
                  </button>
                  <div className="absolute left-0 mt-1 w-56 bg-white border border-gray-100 rounded-xl shadow-xl opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all">
                    <div className="py-2">
                      {section.items.map((item) => (
                        <a
                          key={item.url}
                          href={item.url}
                          target="_blank"
                          rel="noopener noreferrer"
                          className="block px-4 py-2.5 text-sm text-gray-700 hover:bg-orange-50 hover:text-orange-600 transition-colors"
                        >
                          {item.name}
                        </a>
                      ))}
                    </div>
                  </div>
                </div>
              ))}
              
              <a
                href="https://anla.my.id"
                target="_blank"
                rel="noopener noreferrer"
                className="ml-4 px-6 py-2.5 bg-gradient-to-r from-orange-500 to-orange-600 text-white rounded-lg hover:from-orange-600 hover:to-orange-700 font-medium shadow-sm transition-all"
              >
                About Me
              </a>
            </div>
          </div>
        </div>
      </nav>

      <section className="bg-gradient-to-r from-orange-500 to-orange-300 text-white relative">
        <div className="container mx-auto px-4 py-20 max-w-7xl">
          <div className="grid md:grid-cols-2 gap-12 items-end">
            <div className="self-start">
              <h1 className="text-5xl font-bold mb-3">Anla Harpanda</h1>
              <p className="text-2xl mb-8 opacity-90">NIM: 2311083015</p>
              <p className="text-lg leading-relaxed opacity-95">
                Microservice ini dibangun dalam arsitektur Kubernetes cluster yang menghubungkan 22 service dengan 16 URL endpoint. 
                Sistem terdiri dari dua domain utama yaitu Perpustakaan dan Marketplace, dilengkapi dengan infrastruktur pendukung 
                seperti service discovery (Eureka), message broker (RabbitMQ), monitoring (Grafana & Prometheus), logging (ELK Stack), 
                dan CI/CD pipeline (Jenkins) untuk otomatisasi deployment.
              </p>
            </div>
            <div className="flex justify-center items-end">
              <img src="/onboard.png" alt="Onboard" className="max-w-md w-full" />
            </div>
          </div>
        </div>
        <div className="absolute bottom-0 left-0 w-full overflow-hidden leading-none">
          <svg className="relative block w-full h-24" viewBox="0 0 1200 120" preserveAspectRatio="none">
            <path d="M0,60 Q300,0 600,60 T1200,60 L1200,120 L0,120 Z" fill="#ffffff"></path>
          </svg>
        </div>
      </section>

      <main className="container mx-auto px-4 py-12 max-w-7xl">
        <div className="space-y-16">
          {services.map((section) => (
            <section key={section.category}>
              <h2 className="text-3xl font-bold text-center mb-8 text-gray-900">{section.category}</h2>
              <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
                {section.items.map((service) => (
                  <a
                    key={service.url}
                    href={service.url}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="block bg-orange-50 border border-gray-200 rounded-lg overflow-hidden shadow-lg hover:shadow-2xl hover:border-orange-500 transition-all"
                  >
                    <div className="h-32 bg-gray-100">
                      <img src={service.img} alt={service.name} className="w-full h-full object-cover" style={{objectPosition: '50% 30%'}} />
                    </div>
                    <div className="p-4">
                      <h3 className="font-semibold text-lg mb-1 text-gray-900">{service.name}</h3>
                      <p className="text-sm text-gray-600 mb-2">{service.desc}</p>
                      <p className="text-xs text-orange-600 truncate">{service.url}</p>
                    </div>
                  </a>
                ))}
              </div>
            </section>
          ))}
        </div>
      </main>
    </div>
  );
}
