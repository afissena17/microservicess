import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Microservice Dashboard",
  description: "16 Active Service Endpoints - Perpustakaan & Marketplace",
  icons: {
    icon: "https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img,w_235,h_235/https://www.pnp.ac.id/wp-content/uploads/2025/01/LOGO-PNP.png",
  },
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        {children}
      </body>
    </html>
  );
}
